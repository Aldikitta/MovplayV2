package com.aldikitta.movplaypt2.screens.home.filmdetails.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.ui.theme.AppBarExpendedHeight
import com.aldikitta.movplaypt2.util.Resource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun FilmInfo(
    scrollState: LazyListState,
    releaseDate: String,
    overview: String,
    casts: Resource<CreditResponse>,
    navigator: DestinationsNavigator
) {
    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Release Date",

                    )
                Text(
                    text = releaseDate,

                    )

            }
        }
    }
}