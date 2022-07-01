package com.aldikitta.movplaypt2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aldikitta.movplaypt2.data.paging.SearchPagingSource
import com.aldikitta.movplaypt2.data.remote.SearchTMDBApi
import com.aldikitta.movplaypt2.model.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MultiSearchRepository @Inject constructor(private val searchApi: SearchTMDBApi) {
    fun multiSearch(queryParam: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                SearchPagingSource(searchApi, queryParam)
            }
        ).flow
    }
}