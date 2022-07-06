package com.aldikitta.movplaypt2.screens.home.filmdetails.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.screens.favorites.FavoritesViewModel
import com.aldikitta.movplaypt2.screens.home.filmdetails.FilmDetailsViewModel
import com.aldikitta.movplaypt2.screens.home.filmdetails.common.FilmImageBanner
import com.aldikitta.movplaypt2.screens.home.filmdetails.common.FilmInfo
import com.aldikitta.movplaypt2.util.Constants
import com.aldikitta.movplaypt2.util.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel(),
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
            FilmInfo(
                scrollState = scrollState,
                releaseDate = details.data?.releaseDate.toString(),
                overview = details.data?.overview.toString(),
                casts = casts,
                navigator = navigator
            )
            FilmImageBanner(
                scrollState = scrollState,
                posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.posterPath}",
                filmName = details.data?.title.toString(),
                filmId = details.data?.id!!,
                filmType = "movie",
                releaseDate = details.data.releaseDate.toString(),
                rating = details.data.voteAverage?.toFloat()!!,
                navigator = navigator,
                viewModel = favoritesViewModel
            )
        }else{
            CircularProgressIndicator()
        }
    }
}