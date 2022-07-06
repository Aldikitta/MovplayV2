package com.aldikitta.movplaypt2.screens.home.details.movie

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldikitta.movplaypt2.screens.home.details.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navigator: DestinationsNavigator,
    viewModel: DetailsViewModel = hiltViewModel(),
    favoritesViewModel
) {
}