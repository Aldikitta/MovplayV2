package com.aldikitta.movplaypt2.screens.home.filmdetails.movie

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            }else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
                }

        }
    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(bottom = 16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//        ) {
//            if (details is Resource.Success) {
//                ImageSection(
//                    posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.backdropPath}",
//                    filmName = details.data?.title.toString(),
//                    filmId = details.data?.id!!,
//                    filmType = "movie",
//                    releaseDate = details.data.releaseDate.toString(),
//                    rating = details.data.voteAverage?.toFloat()!!,
//                    navigator = navigator,
//                    viewModel = favoritesViewModel
//                )
//            } else {
//                CircularProgressIndicator()
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }


//    //Genre
//    Box {
//        if (details is Resource.Success) {
//            FilmInfo(
//                scrollState = scrollState,
//                releaseDate = details.data?.releaseDate.toString(),
//                overview = details.data?.overview.toString(),
//                casts = casts,
//                navigator = navigator
//            )
//            FilmImageBanner(
//                scrollState = scrollState,
//                posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.posterPath}",
//                filmName = details.data?.title.toString(),
//                filmId = details.data?.id!!,
//                filmType = "movie",
//                releaseDate = details.data.releaseDate.toString(),
//                rating = details.data.voteAverage?.toFloat()!!,
//                navigator = navigator,
//                viewModel = favoritesViewModel
//            )
//        }else{
//            CircularProgressIndicator()
//        }
//    }
}

@Composable
fun ImageSection(
    posterUrl: String,
    filmName: String,
    filmId: Int,
    filmType: String,
    releaseDate: String,
    rating: Float,
    navigator: DestinationsNavigator,
    viewModel: FavoritesViewModel,
//    color: Color,
) {
    val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .fillMaxHeight(0.5f)
//            .clip(shape = MaterialTheme.shapes.medium)
        ) {
            Column() {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = posterUrl)
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.placeholder)
                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                )
                FilmNameAndRating(
                    filmName = filmName,
                    rating = rating
                )
            }

        }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        CircularBackButtons(
            onClick = {
                navigator.popBackStack()
            }
        )
        CircularFavoriteButtons(
            isLiked = viewModel.isAFavorite(filmId).observeAsState().value != null,
            onClick = { isFav ->
                if (isFav) {
                    Toast.makeText(context, "Already added to your favorites", Toast.LENGTH_SHORT)
                        .show()
                    return@CircularFavoriteButtons
                } else {
                    viewModel.insertFavorite(
                        Favorite(
                            favorite = true,
                            mediaId = filmId,
                            mediaType = filmType,
                            image = posterUrl,
                            title = filmName,
                            releaseDate = releaseDate,
                            rating = rating
                        )
                    )
                }
            }
        )
    }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
////                .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        CircularBackButtons(
//            onClick = {
//                navigator.popBackStack()
//            }
//        )
//        CircularFavoriteButtons(
//            isLiked = viewModel.isAFavorite(filmId).observeAsState().value != null,
//            onClick = { isFav ->
//                if (isFav) {
//                    Toast.makeText(context, "Already added to your favorites", Toast.LENGTH_SHORT)
//                        .show()
//                    return@CircularFavoriteButtons
//                } else {
//                    viewModel.insertFavorite(
//                        Favorite(
//                            favorite = true,
//                            mediaId = filmId,
//                            mediaType = filmType,
//                            image = posterUrl,
//                            title = filmName,
//                            releaseDate = releaseDate,
//                            rating = rating
//                        )
//                    )
//                }
//            }
//        )
//    }
}