package com.yom.ahati.ui.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yom.ahati.view_model.VideosViewModel

@Composable
fun Videos() {
    Box(modifier = Modifier.background(color = Color.Black)) {
        VideosList()
    }
}


@Composable
private fun VideosList(videosViewModel: VideosViewModel = viewModel()) {

    val videos = videosViewModel.videos.value ?: emptyList()

    LazyColumn{

        itemsIndexed(videos) { _, video ->
            Box(
                modifier = Modifier.fillParentMaxSize(),
            ) {

                VideoPlayer(uri = Uri.parse(video.videoUrl))

                Column(
                    modifier = Modifier.align(Alignment.BottomStart),
                ) {
                }
            }
        }
    }
}

