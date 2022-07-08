package com.aldikitta.movplaypt2.screens.home.filmdetails.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularBackButtons(
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.padding(top = 8.dp),
        onClick = { onClick() },
        shape = CircleShape,
       colors = ButtonDefaults.buttonColors(
           containerColor = Color.White.copy(alpha = 0.3f),
            contentColor = Color.White
       )
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}