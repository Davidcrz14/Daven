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

    private fun applyCurrentFilter() {
        _videos.value = when (currentFilter) {
            "recent" -> allVideos.sortedByDescending { it.dateAdded }.take(20)
            "favorites" -> allVideos.filter { it.isFavorite }
            "folders" -> allVideos.groupBy { it.folder }.values.flatten()
            else -> allVideos
        }
    }

    fun toggleFavorite(video: Video) {
        val updatedVideos = allVideos.map {
            if (it.id == video.id) it.copy(isFavorite = !it.isFavorite) else it
        }
        allVideos = updatedVideos
        applyCurrentFilter()
    }

    fun searchVideos(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val searchResults = repository.searchVideos(query)
                _videos.value = searchResults
            } catch (e: Exception) {
                _videos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
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
