package com.aldikitta.movplaypt2.data.repository

import com.aldikitta.movplaypt2.BuildConfig.API_KEY
import com.aldikitta.movplaypt2.data.repository.responses.MovieResponse
import com.aldikitta.movplaypt2.util.Constants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieResponse
}