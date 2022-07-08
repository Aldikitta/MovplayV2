package com.aldikitta.movplaypt2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.paging.SearchPagingSource
import com.aldikitta.movplaypt2.data.remote.SearchTMDBApi
import com.aldikitta.movplaypt2.data.remote.responses.MultiSearchResponse
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieDetails
import com.aldikitta.movplaypt2.data.remote.responses.movieresponses.MovieResponse
import com.aldikitta.movplaypt2.model.Search
import com.aldikitta.movplaypt2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MultiSearchRepository @Inject constructor(private val searchApi: SearchTMDBApi) {
    fun multiSearch(queryParam: String) = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 1),
            pagingSourceFactory = {
                SearchPagingSource(searchApi, queryParam)
            }
        ).flow
}