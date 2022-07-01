package com.aldikitta.movplaypt2.di

import com.aldikitta.movplaypt2.data.remote.SearchTMDBApi
import com.aldikitta.movplaypt2.data.repository.MultiSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MultiSearchModule {
    @Singleton
    @Provides
    fun provideMultiSearchRepository(searchApi: SearchTMDBApi) = MultiSearchRepository(searchApi)
}