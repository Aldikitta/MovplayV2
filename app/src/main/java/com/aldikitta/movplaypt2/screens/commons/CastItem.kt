package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R


@Composable
fun CastItem(
    size: Dp,
    castName: String,
    castImageUrl: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = castImageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.placeholder)
                        crossfade(true)
                    }).build()
            ),
            modifier = Modifier
                .fillMaxSize()
                .height(size)
                .padding(8.dp)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            contentDescription = "Character"
        )

        Text(
            text = castName,
        )
    }
}