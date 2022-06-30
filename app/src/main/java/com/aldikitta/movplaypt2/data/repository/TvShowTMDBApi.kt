package com.aldikitta.movplaypt2.data.repository

import com.aldikitta.movplaypt2.BuildConfig.API_KEY
import com.aldikitta.movplaypt2.util.Constants.LANGUAGE_EN
import com.aldikitta.movplaypt2.util.Constants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowTMDBApi {
    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    )

    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    )
}