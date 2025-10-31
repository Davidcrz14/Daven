package com.daven.videoplayer.repository

import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.daven.videoplayer.model.Video
import com.daven.videoplayer.viewmodel.RenameResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(private val context: Context) {

    private val contentResolver: ContentResolver = context.contentResolver

    suspend fun getAllVideos(): List<Video> = withContext(Dispatchers.IO) {
        val videos = mutableListOf<Video>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )

        val selection = "${MediaStore.Video.Media.DURATION} > ?"
        val selectionArgs = arrayOf("1000") // Videos longer than 1 second
        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"

        val cursor: Cursor? = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
            val displayNameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val dateAddedColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val mimeTypeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val bucketColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val title = it.getString(titleColumn) ?: "Unknown"
                val displayName = it.getString(displayNameColumn) ?: "Unknown"
                val duration = it.getLong(durationColumn)
                val size = it.getLong(sizeColumn)
                val path = it.getString(dataColumn) ?: ""
                val dateAdded = it.getLong(dateAddedColumn)
                val mimeType = it.getString(mimeTypeColumn) ?: ""
                val folder = it.getString(bucketColumn) ?: "Unknown"

                val uri = Uri.withAppendedPath(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )

                videos.add(
                    Video(
                        id = id,
                        title = title,
                        displayName = displayName,
                        duration = duration,
                        size = size,
                        path = path,
                        uri = uri,
                        dateAdded = dateAdded,
                        mimeType = mimeType,
                        folder = folder
                    )
                )
            }
        }

        videos
    }

    suspend fun getVideosByFolder(): Map<String, List<Video>> = withContext(Dispatchers.IO) {
        getAllVideos().groupBy { it.folder }
    }

    suspend fun searchVideos(query: String): List<Video> = withContext(Dispatchers.IO) {
        getAllVideos().filter {
            it.title.contains(query, ignoreCase = true) ||
            it.displayName.contains(query, ignoreCase = true)
        }
    }

    suspend fun renameVideo(video: Video, newName: String): RenameResult = withContext(Dispatchers.IO) {
        val trimmed = newName.trim()
        if (trimmed.isBlank()) return@withContext RenameResult.InvalidName

        val extension = video.displayName.substringAfterLast('.', "")
        val baseName = if (extension.isNotBlank() && trimmed.endsWith(".$extension", ignoreCase = true)) {
            trimmed.substringBeforeLast('.')
        } else {
            trimmed
        }

        val finalDisplayName = if (extension.isNotBlank()) "$baseName.$extension" else baseName
        val titleWithoutExtension = if (extension.isNotBlank()) baseName else finalDisplayName

        val values = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, finalDisplayName)
            put(MediaStore.Video.Media.TITLE, titleWithoutExtension)
        }

        return@withContext try {
            val updatedRows = contentResolver.update(video.uri, values, null, null)
            if (updatedRows > 0) {
                val updatedPath = buildUpdatedPath(video.path, finalDisplayName)
                val updatedVideo = video.copy(
                    title = baseName,
                    displayName = finalDisplayName,
                    path = updatedPath
                )
                RenameResult.Success(updatedVideo)
            } else {
                RenameResult.Failure()
            }
        } catch (recoverable: RecoverableSecurityException) {
            val intentSender = recoverable.userAction.actionIntent.intentSender
            RenameResult.RequiresPermission(intentSender)
        } catch (security: SecurityException) {
            RenameResult.Failure(security)
        } catch (throwable: Throwable) {
            RenameResult.Failure(throwable)
        }
    }

    private fun buildUpdatedPath(originalPath: String, displayName: String): String {
        val lastSlashIndex = originalPath.lastIndexOf('/')
        if (lastSlashIndex < 0) return displayName
        return originalPath.substring(0, lastSlashIndex + 1) + displayName
    }
}
