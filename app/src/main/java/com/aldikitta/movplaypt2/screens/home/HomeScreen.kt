package com.aldikitta.movplaypt2.screens.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.aldikitta.movplaypt2.R
import com.aldikitta.movplaypt2.model.Genre
import com.aldikitta.movplaypt2.screens.commons.MovplayToolbar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    //Movies
    val trendingMovies = viewModel.trendingMovies.value.collectAsLazyPagingItems()
    val upComingMovies = viewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.value.collectAsLazyPagingItems()

    //Tv Shows
    val trendingTvShows = viewModel.trendingTvShows.value.collectAsLazyPagingItems()
    val onAirTvShows = viewModel.onAirTvShows.value.collectAsLazyPagingItems()
    val topRatedTvShows = viewModel.topRatedTvShows.value.collectAsLazyPagingItems()
    val airingTodayTvShows = viewModel.airingTvShows.value.collectAsLazyPagingItems()
    val popularTvShows = viewModel.popularTvShows.value.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovplayToolbar(
            navigator = navigator,
            title = {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.movcolored),
                        contentDescription = "Movplay Logo",
                        modifier = Modifier.height(35.dp),
                    )
                }
            },
            showBackArrow = false,
            navActions = {
                IconButton(
                    onClick = {
//                        navigator.navigate(SearchScreenDestination)
                    },
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            }
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            item {
                Category(
                    items = listOf("Movies", "Tv Shows"),
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            item {
                Text(
                    text = "Genres",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium
                )
            }
            item {
                GenreOptionV2(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Category(
    items: List<String>,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val currentOption = MaterialTheme.colorScheme.primary
    val option = MaterialTheme.colorScheme.primaryContainer

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            val lineLength = animateFloatAsState(
                targetValue = if (item == viewModel.selectedOption.value) 2f else 0f,
                animationSpec = tween(
                    durationMillis = 200
                )
            )

            Text(
                text = item,
                color = if (item == viewModel.selectedOption.value) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .drawBehind {
                        if (item == viewModel.selectedOption.value) {
                            if (lineLength.value > 0f) {
                                drawLine(
                                    color = if (item == viewModel.selectedOption.value) {
                                        currentOption
                                    } else {
                                        Color.Magenta
                                    },
                                    start = Offset(
                                        size.width / 2f - lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    end = Offset(
                                        size.width / 2f + lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    strokeWidth = 5.dp.toPx(),
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        viewModel.setSelectedOption(item)
                    }
            )
        }
    }
}

@Composable
fun GenreOption(
    viewModel: HomeViewModel
) {
    val genres = if (viewModel.selectedOption.value == "Tv Shows") {
        viewModel.tvShowsGenres.value
    } else {
        viewModel.moviesGenres.value
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = genres) { genre ->
            Text(
                text = genre.name,
//                style = typography.body1.merge(),
//                color = Color.White,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 8.dp,
                        ),
                    )
                    .clickable {
                        if (viewModel.selectedOption.value == "Movies") {
                            viewModel.setGenre(genre.name)
                            viewModel.getTrendingMovies(genre.id)
                            viewModel.getTopRatedMovies(genre.id)
                            viewModel.getUpComingMovies(genre.id)
                            viewModel.getNowPlayingMovies(genre.id)
                            viewModel.getPopularMovies(genre.id)
                        } else if (viewModel.selectedOption.value == "Tv Shows") {
                            viewModel.setGenre(genre.name)
                            viewModel.getTrendingTvShows(genre.id)
                            viewModel.getTopRatedTvShows(genre.id)
                            viewModel.getAiringTodayTvShows(genre.id)
                            viewModel.getOnTheAirTvShows(genre.id)
                            viewModel.getPopularTvShows(genre.id)
                        }
                    }
                    .background(
                        if (genre.name == viewModel.selectedGenre.value) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                    .padding(
                        end = 8.dp,
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreOptionV2(
    viewModel: HomeViewModel
) {
    val genres = if (viewModel.selectedOption.value == "Tv Shows") {
        viewModel.tvShowsGenres.value
    } else {
        viewModel.moviesGenres.value
    }
    val selected by remember {
        mutableStateOf(true)
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = genres) { genre ->
            FilterChip(
                selected = if (genre.name == viewModel.selectedGenre.value) {
                    selected
                } else {
                    !selected
                },
                onClick = {
                    if (viewModel.selectedOption.value == "Movies") {
                        viewModel.setGenre(genre.name)
                        viewModel.getTrendingMovies(genre.id)
                        viewModel.getTopRatedMovies(genre.id)
                        viewModel.getUpComingMovies(genre.id)
                        viewModel.getNowPlayingMovies(genre.id)
                        viewModel.getPopularMovies(genre.id)
                    } else if (viewModel.selectedOption.value == "Tv Shows") {
                        viewModel.setGenre(genre.name)
                        viewModel.getTrendingTvShows(genre.id)
                        viewModel.getTopRatedTvShows(genre.id)
                        viewModel.getAiringTodayTvShows(genre.id)
                        viewModel.getOnTheAirTvShows(genre.id)
                        viewModel.getPopularTvShows(genre.id)
                    }
                },
                label = { Text(genre.name) },
                selectedIcon = {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                },
                modifier = Modifier.padding(end = 5.dp)
            )
        }
    }
}