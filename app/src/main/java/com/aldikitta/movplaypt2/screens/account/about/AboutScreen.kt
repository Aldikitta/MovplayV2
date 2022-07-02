package com.aldikitta.movplaypt2.screens.account.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.screens.commons.MovplayToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AboutScreen(
    navigator: DestinationsNavigator
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovplayToolbar(
            navigator = navigator,
            title = {
                Text(text = "About")
            },
            showBackArrow = true
        )
        Image(painter = painterResource(id = R.drawable.movcolored), contentDescription = null)
    }
}