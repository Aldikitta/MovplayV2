package com.aldikitta.movplaypt2.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aldikitta.movplaypt2.data.repository.movie.MoviesGenresRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsGenresRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesGenresRepository: MoviesGenresRepository,
    private val tvShowsRepository: TvShowsRepository,
    private val tvShowsGenresRepository: TvShowsGenresRepository
) : ViewModel() {
    private val _selectedOption = mutableStateOf("Movies")
    val selectedOption: State<String> = _selectedOption

    private val _selectedGenre = mutableStateOf("")
    val selectedGenre: State<String> = _selectedGenre
}