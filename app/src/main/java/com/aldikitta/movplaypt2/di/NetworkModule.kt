package com.aldikitta.movplaypt2.di

import android.app.Application
import androidx.room.Room
import com.aldikitta.movplaypt2.data.local.FavoritesDatabase
import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.data.remote.SearchTMDBApi
import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.data.repository.MultiSearchRepository
import com.aldikitta.movplaypt2.data.repository.movie.MovieDetailsRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesGenresRepository
import com.aldikitta.movplaypt2.data.repository.movie.MoviesRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowDetailsRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsGenresRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowsRepository
import com.aldikitta.movplaypt2.util.Constants.BASE_URL
import com.aldikitta.movplaypt2.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideMovieTMDPApi(okHttpClient: OkHttpClient): MovieTMDBApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieTMDBApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTvShowTMDPApi(okHttpClient: OkHttpClient): TvShowTMDBApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TvShowTMDBApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchTMDPApi(okHttpClient: OkHttpClient): SearchTMDBApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SearchTMDBApi::class.java)
    }

}