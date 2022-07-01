package com.aldikitta.movplaypt2.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.aldikitta.movplaypt2.screens.destinations.AccountScreenDestination
import com.aldikitta.movplaypt2.screens.destinations.Destination
import com.aldikitta.movplaypt2.screens.destinations.FavoritesScreenDestination
import com.aldikitta.movplaypt2.screens.destinations.HomeScreenDestination

sealed class BottomNavItem(
    val title: String,
    val icon: @Composable () -> Unit,
    val destination: Destination
) {
    object Home : BottomNavItem(
        "Home", {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
        }, HomeScreenDestination
    )

    object Favorites : BottomNavItem(
        "Favorites", {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites")
        }, FavoritesScreenDestination
    )

    object Account : BottomNavItem(
        "Account", {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Account")
        }, AccountScreenDestination
    )
}