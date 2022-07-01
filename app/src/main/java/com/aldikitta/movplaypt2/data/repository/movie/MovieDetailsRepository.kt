package com.aldikitta.movplaypt2.data.repository.movie

import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.util.Resource
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val movieApi: MovieTMDBApi,
) {
    //Movie Details
    suspend fun getMoviesDetails(movieId: Int): Resource<MovieDetails> {
        val response = try {
            movieApi.getMovieDetails(movieId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

    //Movie Casts
    suspend fun getMovieCasts(movieId: Int): Resource<CreditResponse> {
        val response = try {
            movieApi.getMovieCredits(movieId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movie Casts $response")
        return Resource.Success(response)
    }
}