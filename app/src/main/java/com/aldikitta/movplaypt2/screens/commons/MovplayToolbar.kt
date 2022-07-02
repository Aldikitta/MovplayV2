package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MovplayToolbar(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    showBackArrow: Boolean = false,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    SmallTopAppBar(
        title = title,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(onClick = { navigator.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            } else null
        },
        actions = navActions
    )
}