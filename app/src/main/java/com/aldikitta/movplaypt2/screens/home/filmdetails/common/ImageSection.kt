package com.aldikitta.movplaypt2.screens.home.filmdetails.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.data.local.Favorite
import com.aldikitta.movplaypt2.screens.favorites.FavoritesViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
}