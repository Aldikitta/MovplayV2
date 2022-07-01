package com.aldikitta.movplaypt2.data.repository.tvshow

import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.CreditResponse
import com.aldikitta.movplaypt2.data.remote.responses.tvshowresponses.TvShowDetails
import com.aldikitta.movplaypt2.util.Resource
import timber.log.Timber
import javax.inject.Inject

class TvShowDetailsRepository @Inject constructor(private val tvShowApi: TvShowTMDBApi) {
    //Tv Shows Details
    suspend fun getTvShowsDetails(tvId: Int): Resource<TvShowDetails> {
        val response = try {
            tvShowApi.getTvShowDetails(tvId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Tv Shows details: $response")
        return Resource.Success(response)
    }

    //Tv Shows Casts
    suspend fun getTvShowsCasts(tvId: Int): Resource<CreditResponse> {
        val response = try {
            tvShowApi.getTvShowCredits(tvId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Tv Shows casts: $response")
        return Resource.Success(response)
    }

}