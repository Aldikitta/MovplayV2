package com.aldikitta.movplaypt2.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
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
    val trendingMovies = viewModel.trendingMovies.value.collectAsLazyPagingItems()
    val upComingMovies = viewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.value.collectAsLazyPagingItems()
}