package com.daven.videoplayer.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.daven.videoplayer.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimation()
    }

    private fun startAnimation() {
        // Animación del logo
        val logoScaleX = ObjectAnimator.ofFloat(binding.logoImage, "scaleX", 0.5f, 1f)
        val logoScaleY = ObjectAnimator.ofFloat(binding.logoImage, "scaleY", 0.5f, 1f)
        val logoAlpha = ObjectAnimator.ofFloat(binding.logoImage, "alpha", 0f, 1f)

        val logoAnimator = AnimatorSet().apply {
            playTogether(logoScaleX, logoScaleY, logoAlpha)
            duration = 600
            interpolator = AccelerateDecelerateInterpolator()
        }

        // Animación del nombre
        val nameAlpha = ObjectAnimator.ofFloat(binding.appName, "alpha", 0f, 1f)
        val nameTranslation = ObjectAnimator.ofFloat(binding.appName, "translationY", 20f, 0f)

        val nameAnimator = AnimatorSet().apply {
            playTogether(nameAlpha, nameTranslation)
            duration = 400
            startDelay = 300
        }

        // Animación del subtítulo
        val subtitleAlpha = ObjectAnimator.ofFloat(binding.subtitle, "alpha", 0f, 1f)
        val subtitleTranslation = ObjectAnimator.ofFloat(binding.subtitle, "translationY", 20f, 0f)

        val subtitleAnimator = AnimatorSet().apply {
            playTogether(subtitleAlpha, subtitleTranslation)
            duration = 400
            startDelay = 500
        }

        // Animación del progress bar
        val progressAlpha = ObjectAnimator.ofFloat(binding.progressBar, "alpha", 0f, 1f)
        progressAlpha.duration = 300
        progressAlpha.startDelay = 700

        // Ejecutar todas las animaciones
        val mainAnimator = AnimatorSet().apply {
            playSequentially(logoAnimator, nameAnimator, subtitleAnimator, progressAlpha)
        }

        mainAnimator.doOnEnd {
            // Esperar un poco más y luego ir a MainActivity
            binding.root.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 800)
        }

        mainAnimator.start()
    }
}
