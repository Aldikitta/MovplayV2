package com.aldikitta.movplaypt2.screens.account

import androidx.compose.runtime.Composable
import com.aldikitta.movplaypt2.screens.account.about.AboutScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator

) {
    AboutScreen(navigator = navigator)
}