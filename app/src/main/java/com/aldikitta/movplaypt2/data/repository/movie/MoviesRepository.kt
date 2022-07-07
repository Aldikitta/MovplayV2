package com.aldikitta.movplaypt2.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.paging.movie.*
import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieApi: MovieTMDBApi) {
    fun getTrendingMoviesThisWeek(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingMoviesSource(movieApi)
            }
        ).flow
    }

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                UpcomingMoviesSource(movieApi)
            }
        ).flow
    }

    fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TopRatedMoviesSource(movieApi)
            }
        ).flow
    }

    fun getNowPlayingMovies() = Pager(
        pagingSourceFactory = {
            NowPlayingMoviesSource(movieApi)
        },
        config = PagingConfig(pageSize = 1)
    ).flow
//    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
//        return Pager(
//            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
//            pagingSourceFactory = {
//                NowPlayingMoviesSource(movieApi)
//            }
//        ).flow
//    }

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                PopularMoviesSource(movieApi)
            }
        ).flow
    }
}