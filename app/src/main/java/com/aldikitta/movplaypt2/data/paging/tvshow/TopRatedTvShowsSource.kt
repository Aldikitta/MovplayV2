package com.aldikitta.movplaypt2.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aldikitta.movplaypt2.data.remote.TvShowTMDBApi
import com.aldikitta.movplaypt2.model.tvshow.TvShow
import retrofit2.HttpException
import java.io.IOException

class TopRatedTvShowsSource(private val api: TvShowTMDBApi) :
    PagingSource<Int, TvShow>() {
    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val nextPage = params.key ?: 1
            val topRatedTvShows = api.getTopRatedTvShows(nextPage)
            LoadResult.Page(
                data = topRatedTvShows.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (topRatedTvShows.results.isEmpty()) null else topRatedTvShows.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}