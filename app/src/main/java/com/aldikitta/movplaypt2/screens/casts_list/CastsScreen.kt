package com.aldikitta.movplaypt2.screens.casts_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.screens.commons.CastItem
import com.aldikitta.movplaypt2.screens.commons.MovplayToolbar
import com.aldikitta.movplaypt2.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination
@Composable
fun CastsScreen(
    creditsResponse: CreditResponse,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        MovplayToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Casts",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            showBackArrow = true
        )

        Timber.d(creditsResponse.cast[0].profilePath)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(creditsResponse.cast) { cast ->
                Timber.d(cast.toString())

                val imageLink = if (cast.profilePath.equals("") || cast.profilePath == null) {
                    "https://pixy.org/src/9/94083.png"
                } else {
                    cast.profilePath
                }

                CastItem(
                    size = 170.dp,
                    castImageUrl = "${Constants.IMAGE_BASE_URL}/$imageLink",
                    castName = cast.name
                )
            }
        }
    }
}