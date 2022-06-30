package com.aldikitta.movplaypt2.data.remote

import com.aldikitta.movplaypt2.BuildConfig.API_KEY
import com.aldikitta.movplaypt2.data.remote.responses.MultiSearchResponse
import com.aldikitta.movplaypt2.util.Constants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTMDBApi {
    @GET("search/multi")
    suspend fun multiSearch(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MultiSearchResponse
}