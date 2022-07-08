package com.aldikitta.movplaypt2.data.repository.tvshow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.paging.tvshow.*
import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.model.tvshow.TvShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowsRepository @Inject constructor(private val tvShowApi: TvShowTMDBApi) {
    fun getAiringTodayTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                AiringTodayTvShowsSource(tvShowApi)
            }
        ).flow

    fun getOnTheAirTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                OnTheAirTvShowsSource(tvShowApi)
            }
        ).flow

    fun getPopularTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                PopularTvShowsSource(tvShowApi)
            }
        ).flow

    fun getTopRatedTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TopRatedTvShowsSource(tvShowApi)
            }
        ).flow

    fun getTrendingThisWeekTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingTvShowsSource(tvShowApi)
            }
        ).flow
}