package com.aldikitta.movplaypt2.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aldikitta.movplaypt2.data.remote.MovieTMDBApi
import com.aldikitta.movplaypt2.model.movie.Movie
import retrofit2.HttpException
import java.io.IOException

class NowPlayingMoviesSource(private val api: MovieTMDBApi) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val nowPlayingMovies = api.getNowPlayingMovies(nextPage)
            LoadResult.Page(
                data = nowPlayingMovies.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nowPlayingMovies.searches.isEmpty()) null else nowPlayingMovies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}