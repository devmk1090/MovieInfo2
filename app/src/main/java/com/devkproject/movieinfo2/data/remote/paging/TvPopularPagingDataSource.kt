package com.devkproject.movieinfo2.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devkproject.movieinfo2.data.model.TvItem
import com.devkproject.movieinfo2.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TvPopularPagingDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, TvItem>() {
    override fun getRefreshKey(state: PagingState<Int, TvItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvItem> {
        return try {
            val nextPage = params.key ?: 1
            val tvList = apiService.getTvPopularList(nextPage)
            LoadResult.Page(
                data = tvList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvList.results.isNotEmpty()) tvList.page + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }
}