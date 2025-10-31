package com.daven.videoplayer.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.list.listItems
import com.daven.videoplayer.R
import com.daven.videoplayer.adapter.VideoAdapter
import com.daven.videoplayer.databinding.ActivityMainBinding
import com.daven.videoplayer.model.Video
import com.daven.videoplayer.viewmodel.MainViewModel
import com.daven.videoplayer.viewmodel.MainViewModelFactory
import com.daven.videoplayer.viewmodel.RenameResult
import com.google.android.material.search.SearchView
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoAdapter: VideoAdapter
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(this) }
    private var isCurrentlyLoading: Boolean = false

    private data class PendingRename(val video: Video, val requestedName: String)

    private var pendingRename: PendingRename? = null

    private val editPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        val pending = pendingRename ?: return@registerForActivityResult
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.renameVideo(pending.video, pending.requestedName) { renameResult ->
                handleRenameResult(pending.video, pending.requestedName, renameResult)
            }
        } else {
            Snackbar.make(binding.root, R.string.rename_cancelled, Snackbar.LENGTH_SHORT).show()
            pendingRename = null
        }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            loadVideos()
        } else {
            showPermissionDeniedMessage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verificar si se abrió desde un archivo de video
        if (handleVideoIntent()) {
            return
        }

        setupUI()
        setupRecyclerView()
        setupObservers()
        checkPermissionAndLoadVideos()
    }

    private fun handleVideoIntent(): Boolean {
        if (intent?.action == Intent.ACTION_VIEW && intent?.data != null) {
            val videoUri = intent.data
            val videoTitle = intent.data?.lastPathSegment ?: "Video"

            val playerIntent = Intent(this, PlayerActivity::class.java).apply {
                putExtra("video_uri", videoUri.toString())
                putExtra("video_title", videoTitle)
            }
            startActivity(playerIntent)
            finish()
            return true
        }
        return false
    }

    private fun setupUI() {
        // Setup filter chips
        binding.chipAll.setOnClickListener { viewModel.filterVideos("all") }
        binding.chipRecent.setOnClickListener { viewModel.filterVideos("recent") }
        binding.chipFolders.setOnClickListener { viewModel.filterVideos("folders") }

        setupSearch()

        binding.favoriteButton.setOnClickListener {
            viewModel.filterVideos("favorites")
        }
    }

    private fun setupSearch() {
        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView.editText.hint = getString(R.string.search_hint)
        binding.searchView.editText.doAfterTextChanged { editable ->
            val query = editable?.toString()?.trim().orEmpty()
            binding.searchBar.setText(query)
            viewModel.updateSearchQuery(query)
        }

        binding.searchView.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.searchView.hide()
                true
            } else {
                false
            }
        }

        binding.searchView.addTransitionListener { _, _, newState ->
            if (newState == SearchView.TransitionState.HIDDEN) {
                binding.searchBar.setText(binding.searchView.editText.text)
            }
        }
    }

    private fun setupRecyclerView() {
        videoAdapter = VideoAdapter(
            onVideoClick = { video -> playVideo(video) },
            onFavoriteClick = { video -> toggleFavorite(video) },
            onMoreClick = { video -> showMoreOptions(video) }
        )

        binding.videosRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.videos.collect { videos ->
                videoAdapter.submitList(videos)
                updateEmptyState(videos.isEmpty())
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                isCurrentlyLoading = isLoading
                binding.loadingProgress.isVisible = isLoading
                binding.filterScrollView.isVisible = !isLoading || videoAdapter.itemCount > 0
                binding.searchBar.isEnabled = !isLoading

                if (isLoading) {
                    binding.videosRecyclerView.isVisible = false
                    binding.emptyStateLayout.isVisible = false
                } else {
                    updateEmptyState(videoAdapter.itemCount == 0)
                }
            }
        }
    }

    private fun checkPermissionAndLoadVideos() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_VIDEO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                loadVideos()
            }
            else -> {
                permissionLauncher.launch(permission)
            }
        }
    }

    private fun loadVideos() {
        viewModel.loadVideos()
    }

    private fun playVideo(video: Video) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra("video_uri", video.uri.toString())
            putExtra("video_title", video.title)
        }
        startActivity(intent)
    }

    private fun toggleFavorite(video: Video) {
        viewModel.toggleFavorite(video)
    }

    private fun showMoreOptions(video: Video) {
        MaterialDialog(this).show {
            title(text = video.displayName)
            listItems(items = listOf(getString(R.string.action_rename))) { _, index, _ ->
                when (index) {
                    0 -> showRenameDialog(video)
                }
            }
            negativeButton(res = android.R.string.cancel)
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        if (isCurrentlyLoading) {
            binding.emptyStateLayout.isVisible = false
            binding.videosRecyclerView.isVisible = false
            return
        }

        val query = binding.searchBar.text?.toString().orEmpty()
        val showEmptyState = isEmpty

        binding.emptyStateLayout.isVisible = showEmptyState
        binding.videosRecyclerView.isVisible = !isEmpty

        if (showEmptyState) {
            binding.emptyStateMessage.text = if (query.isNotBlank()) {
                getString(R.string.search_no_results, query)
            } else {
                getString(R.string.no_videos_found)
            }
        }
    }

    private fun showPermissionDeniedMessage() {
        Toast.makeText(
            this,
            getString(R.string.permission_message),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showRenameDialog(video: Video, initialName: String? = null) {
        val extension = video.displayName.substringAfterLast('.', "")
        val basePrefill = initialName?.takeIf { it.isNotBlank() } ?: if (extension.isNotBlank()) {
            video.displayName.substringBeforeLast('.')
        } else {
            video.displayName
        }

        val prefill = if (extension.isNotBlank() && basePrefill.endsWith(".${extension}", ignoreCase = true)) {
            basePrefill.substringBeforeLast('.')
        } else {
            basePrefill
        }

        MaterialDialog(this).show {
            title(res = R.string.rename_title)
            input(
                hint = if (extension.isNotBlank()) {
                    getString(R.string.rename_hint_with_extension, ".${extension}")
                } else {
                    getString(R.string.rename_hint_extensionless)
                },
                prefill = prefill,
                waitForPositiveButton = true,
                allowEmpty = false
            )
            positiveButton(res = R.string.rename_confirm) { dialog ->
                val requestedName = dialog.getInputField().text.toString().trim()
                requestRename(video, requestedName)
            }
            negativeButton(res = android.R.string.cancel)
        }
    }

    private fun requestRename(video: Video, requestedName: String) {
        val trimmed = requestedName.trim()
        if (trimmed.isBlank()) {
            Snackbar.make(binding.root, R.string.rename_error_empty, Snackbar.LENGTH_SHORT).show()
            return
        }

        viewModel.renameVideo(video, trimmed) { result ->
            handleRenameResult(video, trimmed, result)
        }
    }

    private fun handleRenameResult(
        video: Video,
        requestedName: String,
        result: RenameResult
    ) {
        when (result) {
            is RenameResult.Success -> {
                pendingRename = null
                val newName = result.updatedVideo.displayName
                Snackbar.make(
                    binding.root,
                    getString(R.string.rename_success, newName),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            is RenameResult.RequiresPermission -> {
                pendingRename = PendingRename(video, requestedName)
                Snackbar.make(
                    binding.root,
                    R.string.rename_permission_required,
                    Snackbar.LENGTH_SHORT
                ).show()
                promptForEditPermission(result.intentSender)
            }

            is RenameResult.InvalidName -> {
                pendingRename = null
                Snackbar.make(binding.root, R.string.rename_error_empty, Snackbar.LENGTH_SHORT).show()
                    showRenameDialog(video, requestedName)
            }

            is RenameResult.Failure -> {
                pendingRename = null
                val targetName = buildTargetDisplayName(video, requestedName)
                Snackbar.make(
                    binding.root,
                    getString(R.string.rename_error, targetName),
                    Snackbar.LENGTH_SHORT
                ).show()
                showRenameDialog(video, requestedName)
            }
        }
    }

    private fun promptForEditPermission(intentSender: IntentSender) {
        runCatching {
            val request = IntentSenderRequest.Builder(intentSender).build()
            editPermissionLauncher.launch(request)
        }.onFailure {
            pendingRename = null
            Snackbar.make(binding.root, R.string.rename_permission_failed, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun buildTargetDisplayName(video: Video, requestedName: String): String {
        val trimmed = requestedName.trim()
        val extension = video.displayName.substringAfterLast('.', "")
        if (extension.isBlank()) return trimmed

        val normalizedExtension = ".${extension}"
        return if (trimmed.endsWith(normalizedExtension, ignoreCase = true)) {
            trimmed
        } else {
            "$trimmed.$extension"
        }
    }
}
