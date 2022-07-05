package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R

@Composable
fun MovieItem(modifier: Modifier = Modifier, imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.splash_icon)
                    crossfade(true)
                }).build()
        ),
        contentDescription = "banner",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}