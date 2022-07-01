package com.aldikitta.movplaypt2.di

import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowDetailsRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsGenresRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvShowModule {
    @Singleton
    @Provides
    fun provideTvShowsDetailsRepository(tvShowApi: TvShowTMDBApi) =
        TvShowDetailsRepository(tvShowApi)

    @Singleton
    @Provides
    fun provideTvShowsGenresRepository(tvShowApi: TvShowTMDBApi) =
        TvShowsGenresRepository(tvShowApi)

    @Singleton
    @Provides
    fun provideTvShowsRepository(tvShowApi: TvShowTMDBApi) = TvShowsRepository(tvShowApi)
}