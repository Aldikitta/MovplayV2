package com.aldikitta.movplaypt2.data.remote

import com.aldikitta.movplaypt2.BuildConfig.API_KEY
import com.aldikitta.movplaypt2.data.remote.responses.tvshowresponses.TVShowResponse
import com.aldikitta.movplaypt2.data.remote.responses.tvshowresponses.TvShowDetails
import com.aldikitta.movplaypt2.util.Constants.LANGUAGE_EN
import com.aldikitta.movplaypt2.util.Constants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowTMDBApi {
    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TvShowDetails

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse
}