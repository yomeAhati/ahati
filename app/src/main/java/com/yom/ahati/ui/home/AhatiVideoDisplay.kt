package com.yom.ahati.ui.home

import android.net.Uri
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.VideoView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

//@Composable
//fun AhatiVideoDisplay(videoUris: List<String>) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(videoUris) { videoUri ->
//            AhatiVideoPlayer1(videoUri, lifecycleOwner)
//            Text(text = "Hello")
//        }
//    }
//}
//@Composable
//fun AhatiVideoPlayer1(videoUri: String, lifecycleOwner: LifecycleOwner) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        // Implement your existing video player here using the provided videoUri
//        // For example:
//        AhatiVideoPlayer(videoUri = videoUri, lifecycleOwner = lifecycleOwner)
//    }
//}
/*
@Composable
fun AhatiVideoPlayer(videoUri: String, lifecycleOwner: LifecycleOwner){
    var isPlaying by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                VideoView(ctx).apply {
                    setVideoURI(Uri.parse(videoUri))
                    if (isPlaying) start()
                    setOnPreparedListener {
                        it.isLooping = true
                    }
                    setOnClickListener {
                        if (isPlaying) pause() else start()
                        isPlaying = !isPlaying
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        AndroidView(
            factory = { ctx ->
                View(ctx).apply {
                    systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_IMMERSIVE
                                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    or View.SYSTEM_UI_FLAG_LOW_PROFILE
                            )
                    setOnSystemUiVisibilityChangeListener { visibility ->
                        if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                            systemUiVisibility = (
                                    View.SYSTEM_UI_FLAG_IMMERSIVE
                                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            or View.SYSTEM_UI_FLAG_LOW_PROFILE
                                    )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    SocialButtons()
}
*/


@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, context.packageName)
                )

                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource( MediaItem.fromUri(uri))

                this.prepare(source)
            }
    }

    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    DisposableEffect(AndroidView(factory = {
        PlayerView(context).apply {
            hideController()
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

            player = exoPlayer
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
    })) {
        onDispose {
            exoPlayer.release()
        }
    }

    SocialButtons()

}