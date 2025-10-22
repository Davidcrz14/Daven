package com.daven.videoplayer.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.daven.videoplayer.R
import com.daven.videoplayer.adapter.VideoAdapter
import com.daven.videoplayer.databinding.ActivityMainBinding
import com.daven.videoplayer.model.Video
import com.daven.videoplayer.viewmodel.MainViewModel
import com.daven.videoplayer.viewmodel.MainViewModelFactory

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videoAdapter: VideoAdapter
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(this) }

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

        // Setup click listeners
        binding.searchButton.setOnClickListener {
            Toast.makeText(this, "Búsqueda próximamente", Toast.LENGTH_SHORT).show()
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.filterVideos("favorites")
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
                binding.loadingProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.videosRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
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
        // TODO: Implement more options (delete, share, etc.)
        Toast.makeText(this, "Opciones para: ${video.title}", Toast.LENGTH_SHORT).show()
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.videosRecyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun showPermissionDeniedMessage() {
        Toast.makeText(
            this,
            getString(R.string.permission_message),
            Toast.LENGTH_LONG
        ).show()
    }
}
