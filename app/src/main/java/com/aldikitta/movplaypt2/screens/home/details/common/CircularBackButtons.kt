package com.aldikitta.movplaypt2.screens.home.details.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun CircularBackButtons(
    onClick: () -> Unit = {}
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back")
    }
}