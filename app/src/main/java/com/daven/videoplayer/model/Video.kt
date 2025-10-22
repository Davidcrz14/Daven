package com.daven.videoplayer.model

import android.net.Uri

data class Video(
    val id: Long,
    val title: String,
    val displayName: String,
    val duration: Long,
    val size: Long,
    val path: String,
    val uri: Uri,
    val dateAdded: Long,
    val mimeType: String,
    val folder: String,
    var isFavorite: Boolean = false,
    var lastPlayedPosition: Long = 0L
) {
    fun getFormattedDuration(): String {
        val seconds = duration / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
        } else {
            String.format("%02d:%02d", minutes, seconds % 60)
        }
    }

    fun getFormattedSize(): String {
        val kb = size / 1024.0
        val mb = kb / 1024.0
        val gb = mb / 1024.0

        return when {
            gb >= 1 -> String.format("%.1f GB", gb)
            mb >= 1 -> String.format("%.1f MB", mb)
            else -> String.format("%.1f KB", kb)
        }
    }
}
