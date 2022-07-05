package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(modifier: Modifier = Modifier, imageUrl: String) {
    Card(
        modifier = modifier.padding(end = 16.dp)
    )
    {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.mipmap.ic_launcher)
                        crossfade(false)
                    }).build()
            ),
            modifier = modifier
                .fillMaxSize(),
            contentDescription = "banner",
            contentScale = ContentScale.Crop

        )
    }
}