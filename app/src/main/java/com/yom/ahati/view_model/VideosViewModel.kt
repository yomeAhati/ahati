package com.yom.ahati.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yom.ahati.data.Video
import com.yom.ahati.data.VideosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val videosRepository: VideosRepository
) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    init {
        loadVideos()
    }

    private fun loadVideos() {
        _videos.value = videosRepository.getVideos()
    }
}