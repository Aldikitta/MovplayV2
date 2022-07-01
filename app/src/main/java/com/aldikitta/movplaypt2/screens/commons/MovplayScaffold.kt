package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aldikitta.movplaypt2.model.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovplayScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Account
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold() { paddingValues ->
        content(paddingValues)

    }
}