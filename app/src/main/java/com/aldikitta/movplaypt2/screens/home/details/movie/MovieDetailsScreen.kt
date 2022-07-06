package com.aldikitta.movplaypt2.screens.home.details.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.screens.favorites.FavoritesViewModel
import com.aldikitta.movplaypt2.screens.home.details.DetailsViewModel
import com.aldikitta.movplaypt2.util.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navigator: DestinationsNavigator,
    viewModel: DetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<MovieDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetails(movieId)
    }.value

    val casts = produceState<Resource<CreditResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieCasts(movieId)
    }.value
    
    //Genre
    Box {
        if (details is Resource.Success) {

        }
    }
}