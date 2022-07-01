package com.aldikitta.movplaypt2.data.repository.movie

import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.GenresResponse
import com.aldikitta.movplaypt2.util.Resource
import timber.log.Timber
import javax.inject.Inject

class MoviesGenresRepository @Inject constructor(private val movieApi: MovieTMDBApi) {
    suspend fun getMoviesGenres(): Resource<GenresResponse> {
        val response = try {
            movieApi.getMovieGenres()
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movies genres: $response")
        return Resource.Success(response)
    }
}