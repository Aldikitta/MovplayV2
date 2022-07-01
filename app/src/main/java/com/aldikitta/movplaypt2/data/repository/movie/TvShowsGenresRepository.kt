package com.aldikitta.movplaypt2.data.repository.movie

import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.GenresResponse
import com.aldikitta.movplaypt2.util.Resource
import timber.log.Timber
import javax.inject.Inject

class TvShowsGenresRepository @Inject constructor(private val tvShowApi: TvShowTMDBApi) {
    suspend fun getTvShowsGenres(): Resource<GenresResponse> {
        val response = try {
            tvShowApi.getTvShowGenres()
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Tv Shows genres; $response")
        return Resource.Success(response)
    }
}