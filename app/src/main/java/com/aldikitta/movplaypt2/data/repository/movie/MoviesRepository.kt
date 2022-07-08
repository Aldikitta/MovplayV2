package com.aldikitta.movplaypt2.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.paging.movie.*
import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieResponse
import com.aldikitta.movplaypt2.model.movie.Movie
import com.aldikitta.movplaypt2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieApi: MovieTMDBApi) {
    fun getTrendingMoviesThisWeek() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 1),
        pagingSourceFactory = {
            TrendingMoviesSource(movieApi)
        }
    ).flow

    fun getUpcomingMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 1),
        pagingSourceFactory = {
            UpcomingMoviesSource(movieApi)
        }
    ).flow


    fun getTopRatedMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 1),
        pagingSourceFactory = {
            TopRatedMoviesSource(movieApi)
        }
    ).flow

    fun getNowPlayingMovies() = Pager(
        pagingSourceFactory = {
            NowPlayingMoviesSource(movieApi)
        },
        config = PagingConfig(pageSize = 1, enablePlaceholders = false)
    ).flow

    fun getPopularMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 1),
        pagingSourceFactory = {
            PopularMoviesSource(movieApi)
        }
    ).flow
}