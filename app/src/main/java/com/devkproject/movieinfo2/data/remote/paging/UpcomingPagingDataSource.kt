package com.devkproject.movieinfo2.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devkproject.movieinfo2.data.model.MovieItem
import com.devkproject.movieinfo2.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpcomingPagingDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val movieList = apiService.getUpcomingMovieList(nextPage)
            LoadResult.Page(
                data = movieList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey =  if (movieList.results.isNotEmpty()) movieList.page + 1 else  null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }
}