package com.aldikitta.movplaypt2.screens.commons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = item.icon,
                            label = {
                                Text(text = item.title)
                            },
                            selected = currentDestination?.route?.contains(item.destination.route) == true,
//                            selected = currentRoute(navController) == item.destination.route,
                            onClick = {
                                navController.navigate(item.destination.route){
                                    navController.graph.startDestinationRoute?.let {screen_route ->
                                        popUpTo(screen_route){
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}