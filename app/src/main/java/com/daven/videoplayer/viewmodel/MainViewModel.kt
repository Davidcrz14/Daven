package com.daven.videoplayer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.daven.videoplayer.model.Video
import com.daven.videoplayer.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var allVideos: List<Video> = emptyList()
    private var currentFilter = "all"
    private var currentQuery = ""

    fun loadVideos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                allVideos = repository.getAllVideos()
                applyCurrentFilter()
            } catch (e: Exception) {
                // Handle error
                _videos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun filterVideos(filter: String) {
        currentFilter = filter
        applyCurrentFilter()
    }

    fun updateSearchQuery(query: String) {
        currentQuery = query
        applyCurrentFilter()
    }

    private fun applyCurrentFilter() {
        var filtered = when (currentFilter) {
            "recent" -> allVideos.sortedByDescending { it.dateAdded }.take(20)
            "favorites" -> allVideos.filter { it.isFavorite }
            "folders" -> allVideos.groupBy { it.folder }.values.flatten()
            else -> allVideos
        }

        if (currentQuery.isNotBlank()) {
            filtered = filtered.filter {
                it.title.contains(currentQuery, ignoreCase = true) ||
                it.displayName.contains(currentQuery, ignoreCase = true)
            }
        }

        _videos.value = filtered
    }

    fun toggleFavorite(video: Video) {
        val updatedVideos = allVideos.map {
            if (it.id == video.id) it.copy(isFavorite = !it.isFavorite) else it
        }
        allVideos = updatedVideos
        applyCurrentFilter()
    }

    fun renameVideo(video: Video, newName: String, onResult: (RenameResult) -> Unit) {
        viewModelScope.launch {
            val result = repository.renameVideo(video, newName)

            if (result is RenameResult.Success) {
                val updatedVideo = result.updatedVideo
                allVideos = allVideos.map { existing ->
                    if (existing.id == updatedVideo.id) {
                        updatedVideo
                    } else {
                        existing
                    }
                }
                applyCurrentFilter()
            }

            onResult(result)
        }
    }
}

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(VideoRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
