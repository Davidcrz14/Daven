package com.daven.videoplayer.ui

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.daven.videoplayer.R
import com.daven.videoplayer.databinding.ActivityPlayerBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private var player: ExoPlayer? = null

    private var playbackPosition = 0L
    private var playWhenReady = true

    // Nuevas variables para funcionalidades
    private var isLoopEnabled = false
    private var isLocked = false
    private lateinit var gestureDetector: GestureDetectorCompat

    private val speedValues = floatArrayOf(0.5f, 0.75f, 1f, 1.25f, 1.5f, 2f)
    private lateinit var speedLabels: Array<String>
    private var currentSpeedIndex = 2

    private val aspectModes = intArrayOf(
        AspectRatioFrameLayout.RESIZE_MODE_FIT,
        AspectRatioFrameLayout.RESIZE_MODE_FILL,
        AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    )
    private lateinit var aspectLabels: Array<String>
    private var currentAspectIndex = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        speedLabels = resources.getStringArray(R.array.playback_speed_labels)
        aspectLabels = resources.getStringArray(R.array.aspect_ratio_labels)
        currentSpeedIndex = speedValues.indexOfFirst { it == 1f }.takeIf { it >= 0 } ?: 2
        binding.speedChip.text = getString(R.string.speed_chip_label, speedValues[currentSpeedIndex])
        binding.speedChip.contentDescription = getString(R.string.speed_button_description, speedValues[currentSpeedIndex])
        binding.playerView.resizeMode = aspectModes[currentAspectIndex]

        setupFullscreen()
        setupGestureDetector()
        setupClickListeners()

        val videoUri = intent.getStringExtra("video_uri")

        if (videoUri != null) {
            initializePlayer(Uri.parse(videoUri))
        } else {
            finish()
        }
    }

    private fun setupFullscreen() {
        // Enable fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, binding.root)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // Keep screen on
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Start with sensor orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.rotateButton.setOnClickListener {
            toggleOrientation()
        }

        binding.lockButton.setOnClickListener {
            toggleLock()
        }

        binding.loopButton.setOnClickListener {
            toggleLoop()
        }

        binding.aspectButton.setOnClickListener {
            cycleAspectRatio()
        }

        binding.speedChip.setOnClickListener {
            showSpeedDialog()
        }

        // Configurar listener para mostrar/ocultar controles al tocar la pantalla
        binding.playerView.setControllerVisibilityListener(
            androidx.media3.ui.PlayerView.ControllerVisibilityListener { visibility ->
                // Sincronizar visibilidad de botones personalizados con controles de ExoPlayer
                if (!isLocked) {
                    val isVisible = visibility == View.VISIBLE
                    binding.backButton.visibility = if (isVisible) View.VISIBLE else View.GONE
                    binding.rotateButton.visibility = if (isVisible) View.VISIBLE else View.GONE
                    binding.playerBottomActions.visibility = if (isVisible) View.VISIBLE else View.GONE
                }
            }
        )
    }

    private fun setupGestureDetector() {
        gestureDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {

            private var totalDistanceX = 0f
            private var totalDistanceY = 0f
            private var isVerticalGesture = false
            private var isHorizontalGesture = false

            override fun onDown(e: MotionEvent): Boolean {
                totalDistanceX = 0f
                totalDistanceY = 0f
                isVerticalGesture = false
                isHorizontalGesture = false
                return true
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (isLocked) return false

                totalDistanceX += abs(distanceX)
                totalDistanceY += abs(distanceY)

                // Determinar si es gesto vertical u horizontal
                if (!isVerticalGesture && !isHorizontalGesture) {
                    if (totalDistanceY > totalDistanceX && totalDistanceY > 30) {
                        isVerticalGesture = true
                    } else if (totalDistanceX > totalDistanceY && totalDistanceX > 30) {
                        isHorizontalGesture = true
                    }
                }

                if (isVerticalGesture) {
                    // Gesto vertical: ajustar brillo
                    val screenWidth = binding.root.width
                    if (e2.x < screenWidth / 2) {
                        // Lado izquierdo: brillo
                        adjustBrightness(-distanceY)
                    } else {
                        // Lado derecho: brillo también (simplificado)
                        adjustBrightness(-distanceY)
                    }
                } else if (isHorizontalGesture) {
                    // Gesto horizontal: avanzar/retroceder
                    seekVideo(-distanceX)
                }

                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isLocked) return false

                val screenWidth = binding.root.width
                player?.let {
                    if (e.x < screenWidth / 3) {
                        // Doble tap izquierdo: retroceder 10s
                        it.seekTo((it.currentPosition - 10000).coerceAtLeast(0))
                    } else if (e.x > screenWidth * 2 / 3) {
                        // Doble tap derecho: avanzar 10s
                        it.seekTo((it.currentPosition + 10000).coerceAtMost(it.duration))
                    } else {
                        // Doble tap centro: play/pause
                        if (it.isPlaying) it.pause() else it.play()
                    }
                }
                return true
            }


        })

        // Configurar gestos personalizados
        binding.playerView.setOnTouchListener { view, event ->
            gestureDetector.onTouchEvent(event)
            false // Siempre permitir que PlayerView maneje el evento también
        }
    }

    private fun adjustBrightness(delta: Float) {
        val layoutParams = window.attributes
        layoutParams.screenBrightness = (layoutParams.screenBrightness + delta / 1000f).coerceIn(0f, 1f)
        window.attributes = layoutParams
    }



    private fun seekVideo(delta: Float) {
        player?.let {
            val seekAmount = (delta * 100).toLong()
            val newPosition = (it.currentPosition + seekAmount).coerceIn(0, it.duration)
            it.seekTo(newPosition)
        }
    }

    private fun toggleLock() {
        isLocked = !isLocked
        binding.lockButton.setImageResource(
            if (isLocked) R.drawable.ic_lock else R.drawable.ic_lock_open
        )

        // Ocultar/mostrar controles
        val visibility = if (isLocked) View.GONE else View.VISIBLE
        binding.backButton.visibility = visibility
        binding.rotateButton.visibility = visibility
        binding.playerBottomActions.visibility = visibility
        if (!isLocked) {
            binding.loopButton.alpha = if (isLoopEnabled) 1f else 0.5f
        }
        binding.playerView.useController = !isLocked
        val message = if (isLocked) R.string.controls_locked else R.string.controls_unlocked
        Snackbar.make(binding.root, getString(message), Snackbar.LENGTH_SHORT).show()
    }

    private fun toggleLoop() {
        isLoopEnabled = !isLoopEnabled
        player?.repeatMode = if (isLoopEnabled) Player.REPEAT_MODE_ONE else Player.REPEAT_MODE_OFF

        binding.loopButton.alpha = if (isLoopEnabled) 1f else 0.5f
        val message = if (isLoopEnabled) R.string.loop_enabled else R.string.loop_disabled
        Snackbar.make(binding.root, getString(message), Snackbar.LENGTH_SHORT).show()
    }


    private fun cycleAspectRatio() {
        currentAspectIndex = (currentAspectIndex + 1) % aspectModes.size
        val mode = aspectModes[currentAspectIndex]
        binding.playerView.resizeMode = mode
        Snackbar.make(
            binding.root,
            getString(R.string.aspect_updated, aspectLabels[currentAspectIndex]),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showSpeedDialog() {
        MaterialDialog(this).show {
            title(res = R.string.speed_dialog_title)
            listItemsSingleChoice(
                items = speedLabels.toList(),
                initialSelection = currentSpeedIndex,
                waitForPositiveButton = false
            ) { _, index, _ ->
                currentSpeedIndex = index
                applyPlaybackSpeed(speedValues[index])
            }
            negativeButton(res = android.R.string.cancel)
        }
    }

    private fun applyPlaybackSpeed(speed: Float) {
        val pitch = player?.playbackParameters?.pitch ?: 1f
        player?.playbackParameters = PlaybackParameters(speed, pitch)
        binding.speedChip.text = getString(R.string.speed_chip_label, speed)
        binding.speedChip.contentDescription = getString(R.string.speed_button_description, speed)
        Snackbar.make(
            binding.root,
            getString(R.string.speed_updated, speed),
            Snackbar.LENGTH_SHORT
        ).show()
    }



    private fun toggleOrientation() {
        requestedOrientation = when (requestedOrientation) {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
    }

    private fun initializePlayer(videoUri: Uri) {
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            binding.playerView.player = exoPlayer

            val mediaItem = MediaItem.fromUri(videoUri)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.seekTo(playbackPosition)
            exoPlayer.prepare()

            // Add listener for player events
            exoPlayer.addListener(object : Player.Listener {
                override fun onVideoSizeChanged(videoSize: VideoSize) {
                    // Detectar orientación del video y ajustar
                    if (videoSize.width > 0 && videoSize.height > 0) {
                        if (videoSize.width > videoSize.height) {
                            // Video horizontal
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                        } else {
                            // Video vertical
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                        }
                    }
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_BUFFERING -> {
                            binding.loadingProgress.visibility = View.VISIBLE
                        }
                        Player.STATE_READY -> {
                            binding.loadingProgress.visibility = View.GONE
                        }
                        Player.STATE_ENDED -> {
                            finish()
                        }
                    }
                }

                override fun onPlayerError(error: PlaybackException) {
                    // Handle playback error
                    binding.loadingProgress.visibility = View.GONE
                    finish()
                }
            })
        }
    }



    override fun onStart() {
        super.onStart()
        if (player == null) {
            val videoUri = intent.getStringExtra("video_uri")
            if (videoUri != null) {
                initializePlayer(Uri.parse(videoUri))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        player?.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player?.let {
            playbackPosition = it.currentPosition
            playWhenReady = it.playWhenReady
            it.playWhenReady = false
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            playWhenReady = it.playWhenReady
            it.release()
        }
        player = null
    }
}

