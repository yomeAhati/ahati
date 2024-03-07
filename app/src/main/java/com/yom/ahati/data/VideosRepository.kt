package com.yom.ahati.data

class VideosRepository {
    private val videos = listOf(
        Video("android.resource://com.yom.ahati/raw/ahati_video"),
        Video("android.resource://com.yom.ahati/raw/ahati_video"),
    )

    fun getVideos(): List<Video> {
        return videos
    }
}
