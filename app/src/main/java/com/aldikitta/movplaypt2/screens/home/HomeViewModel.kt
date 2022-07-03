package com.aldikitta.movplaypt2.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.repository.movie.MoviesGenresRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsGenresRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsRepository
import com.aldikitta.movplaypt2.model.Genre
import com.aldikitta.movplaypt2.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
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

    fun setSelectedOption(selectedOption: String) {
        _selectedOption.value = selectedOption
    }

    fun setGenre(genre: String) {
        _selectedGenre.value = genre
    }

    /**
     * Movies states code block
     */
    private val _trendingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovies: State<Flow<PagingData<Movie>>> = _trendingMovies

    private val _upcomingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Movie>>> = _upcomingMovies

    private val _topRatedMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val topRatedMovies: State<Flow<PagingData<Movie>>> = _topRatedMovies

    private val _nowPlayingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val nowPlayingMovies: State<Flow<PagingData<Movie>>> = _nowPlayingMovies

    private val _popularMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val popularMovies: State<Flow<PagingData<Movie>>> = _popularMovies

    private val _moviesGenres = mutableStateOf<List<Genre>>(emptyList())
    val moviesGenres: State<List<Genre>> = _moviesGenres
}