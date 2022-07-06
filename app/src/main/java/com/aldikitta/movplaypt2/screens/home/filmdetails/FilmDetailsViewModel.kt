package com.aldikitta.movplaypt2.screens.home.filmdetails

import androidx.lifecycle.ViewModel
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.data.remote.responses.tvshowresponses.TvShowDetails
import com.aldikitta.movplaypt2.data.repository.movie.MovieDetailsRepository
import com.aldikitta.movplaypt2.data.repository.tvshow.TvShowDetailsRepository
import com.aldikitta.movplaypt2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val tvShowDetailsRepository: TvShowDetailsRepository
) : ViewModel() {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return movieDetailsRepository.getMoviesDetails(movieId)
    }

    suspend fun getTvShowDetails(tvId: Int): Resource<TvShowDetails> {
        return tvShowDetailsRepository.getTvShowsDetails(tvId)
    }

    suspend fun getMovieCasts(movieId: Int): Resource<CreditResponse> {
        val cast = movieDetailsRepository.getMovieCasts(movieId)
        Timber.d(cast.data.toString())
        return cast
    }

    suspend fun getTvShowCasts(tvId: Int): Resource<CreditResponse> {
        return tvShowDetailsRepository.getTvShowsCasts(tvId)
    }
}