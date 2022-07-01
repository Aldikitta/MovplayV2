package com.aldikitta.movplaypt2.di

import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.data.repository.movie.MovieDetailsRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesGenresRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Singleton
    @Provides
    fun provideMovieDetailsRepository(movieApi: MovieTMDBApi) = MovieDetailsRepository(movieApi)

    @Singleton
    @Provides
    fun provideMoviesGenresRepository(movieApi: MovieTMDBApi) = MoviesGenresRepository(movieApi)

    @Singleton
    @Provides
    fun provideMoviesRepository(movieApi: MovieTMDBApi) = MoviesRepository(movieApi)
}