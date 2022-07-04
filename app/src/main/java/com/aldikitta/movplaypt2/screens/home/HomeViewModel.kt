package com.aldikitta.movplaypt2.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.aldikitta.movplaypt2.data.repository.movie.MoviesGenresRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsGenresRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsRepository
import com.aldikitta.movplaypt2.model.Genre
import com.aldikitta.movplaypt2.model.movie.Movie
import com.aldikitta.movplaypt2.model.tvshow.TvShow
import com.aldikitta.movplaypt2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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

    /**
     * Tv Shows states code block
     */
    private val _trendingTvShows = mutableStateOf<Flow<PagingData<TvShow>>>(emptyFlow())
    val trendingTvShows: State<Flow<PagingData<TvShow>>> = _trendingTvShows

    private val _onAirTvShows = mutableStateOf<Flow<PagingData<TvShow>>>(emptyFlow())
    val onAirTvShows: State<Flow<PagingData<TvShow>>> = _onAirTvShows

    private val _topRatedTvShows = mutableStateOf<Flow<PagingData<TvShow>>>(emptyFlow())
    val topRatedTvShows: State<Flow<PagingData<TvShow>>> = _topRatedTvShows

    private val _airingTvShows = mutableStateOf<Flow<PagingData<TvShow>>>(emptyFlow())
    val airingTvShows: State<Flow<PagingData<TvShow>>> = _airingTvShows

    private val _popularTvShows = mutableStateOf<Flow<PagingData<TvShow>>>(emptyFlow())
    val popularTvShows: State<Flow<PagingData<TvShow>>> = _popularTvShows

    private val _tvShowsGenres = mutableStateOf<List<Genre>>(emptyList())
    val tvShowsGenres: State<List<Genre>> = _tvShowsGenres

    init {
        //movies
        getTrendingMovies(genreId = null)
        getNowPlayingMovies(genreId = null)
        getUpComingMovies(genreId = null)
        getTopRatedMovies(genreId = null)
        getPopularMovies(genreId = null)
        getMoviesGenres()
    }

    /**
     * Movies code block
     */
    fun getTrendingMovies(genreId: Int?) {
        viewModelScope.launch {
            _trendingMovies.value = if (genreId != null) {
                moviesRepository.getTrendingMoviesThisWeek().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTrendingMoviesThisWeek().cachedIn(viewModelScope)
            }
        }
    }

    fun getUpComingMovies(genreId: Int?) {
        viewModelScope.launch {
            _upcomingMovies.value = if (genreId != null) {
                moviesRepository.getUpcomingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedMovies(genreId: Int?) {
        viewModelScope.launch {
            _topRatedMovies.value = if (genreId != null) {
                moviesRepository.getTopRatedMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getNowPlayingMovies(genreId: Int?) {
        viewModelScope.launch {
            _nowPlayingMovies.value = if (genreId != null) {
                moviesRepository.getNowPlayingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getPopularMovies(genreId: Int?) {
        viewModelScope.launch {
            _popularMovies.value = if (genreId != null) {
                moviesRepository.getPopularMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getPopularMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getMoviesGenres() {
        viewModelScope.launch {
            when (val result = moviesGenresRepository.getMoviesGenres()) {
                is Resource.Success -> {
                    _moviesGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {

                }
                else -> {}
            }
        }
    }

    /**
     * Tv Shows code block
     */

    fun getTrendingTvShows(genreId: Int?) {
        viewModelScope.launch {
            _trendingTvShows.value = if (genreId != null) {
                tvShowsRepository.getTrendingThisWeekTvShows().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                tvShowsRepository.getTrendingThisWeekTvShows().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedTvShows(genreId: Int?) {
        viewModelScope.launch {
            _topRatedTvShows.value = if (genreId != null) {
                tvShowsRepository.getTopRatedTvShows().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                tvShowsRepository.getTopRatedTvShows().cachedIn(viewModelScope)
            }
        }
    }

    fun getOnTheAirTvShows(genreId: Int?) {
        viewModelScope.launch {
            _onAirTvShows.value = if (genreId != null) {
                tvShowsRepository.getOnTheAirTvShows().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                tvShowsRepository.getOnTheAirTvShows().cachedIn(viewModelScope)
            }
        }
    }

    fun getAiringTodayTvShows(genreId: Int?) {
        viewModelScope.launch {
            _airingTvShows.value = if (genreId != null) {
                tvShowsRepository.getAiringTodayTvShows().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                tvShowsRepository.getAiringTodayTvShows().cachedIn(viewModelScope)
            }
        }
    }

    fun getPopularTvShows(genreId: Int?) {
        viewModelScope.launch {
            _popularTvShows.value = if (genreId != null) {
                tvShowsRepository.getPopularTvShows().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                tvShowsRepository.getPopularTvShows().cachedIn(viewModelScope)
            }
        }
    }

    fun getTvShowsGenres() {
        viewModelScope.launch {
            when (val result = tvShowsGenresRepository.getTvShowsGenres()) {
                is Resource.Success -> {
                    _tvShowsGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {
                    //loadingError.value = result.message.toString()
                }
                else -> {}
            }
        }
    }
}