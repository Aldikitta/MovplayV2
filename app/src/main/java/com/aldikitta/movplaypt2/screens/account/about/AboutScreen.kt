package com.aldikitta.movplaypt2.screens.account.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.screens.commons.MovplayToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AboutScreen(
    navigator: DestinationsNavigator
) {
    MovplayToolbar(
        navigator = navigator,
        title = {
            Text(text = "About")
        },
        showBackArrow = true
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp, top = 50.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.movcolored),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Text(text = stringResource(id = R.string.version), style = MaterialTheme.typography.titleLarge)
        Text(text = stringResource(id = R.string.about), textAlign = TextAlign.Center)

    }
}