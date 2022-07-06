package com.aldikitta.movplaypt2.screens.home.filmdetails.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.screens.commons.CastItem
import com.aldikitta.movplaypt2.screens.destinations.CastsScreenDestination
import com.aldikitta.movplaypt2.util.Constants
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Composable
fun CastDetails(
    creditsResponse: CreditResponse?,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cast",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View all",
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 18.sp,
                    color = Color.White
                )

                IconButton(onClick = {
                    Timber.d("${creditsResponse == null}")

                    if (creditsResponse == null) {
                        return@IconButton
                    }
                    navigator.navigate(CastsScreenDestination(creditsResponse))
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowRight,
                        contentDescription = null
                    )
                }
            }
        }


        LazyRow(content = {
            items(creditsResponse?.cast!!) { cast ->
                CastItem(
                    size = 90.dp,
                    castImageUrl = "${Constants.IMAGE_BASE_URL}/${cast.profilePath}",
                    castName = cast.name
                )
            }
        })
    }
}