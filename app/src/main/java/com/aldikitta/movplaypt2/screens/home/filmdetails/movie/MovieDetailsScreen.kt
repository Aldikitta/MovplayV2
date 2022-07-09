package com.aldikitta.movplaypt2.screens.home.filmdetails.movie

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.data.local.Favorite
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.screens.favorites.FavoritesViewModel
import com.aldikitta.movplaypt2.screens.home.filmdetails.FilmDetailsViewModel
import com.aldikitta.movplaypt2.screens.home.filmdetails.common.*
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


    LazyColumn() {
        item {
            if (details is Resource.Success) {

                Box {
                    ImageSection(
                        posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.backdropPath}",
                        filmName = details.data?.title.toString(),
                        filmId = details.data?.id!!,
                        filmType = "movie",
                        releaseDate = details.data.releaseDate.toString(),
                        rating = details.data.voteAverage?.toFloat()!!,
                        navigator = navigator,
                        viewModel = favoritesViewModel
                    )
                }
                FilmInfo(
                    scrollState = scrollState,
                    overview = details.data?.overview.toString(),
                    releaseDate = details.data?.releaseDate.toString(),
                    navigator = navigator,
                    casts = casts
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}
