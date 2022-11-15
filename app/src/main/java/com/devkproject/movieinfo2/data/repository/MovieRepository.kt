package com.devkproject.movieinfo2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devkproject.movieinfo2.data.model.Genres
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.remote.ApiService
import com.devkproject.movieinfo2.data.remote.paging.*
import com.devkproject.movieinfo2.utils.network.DataState
import com.devkproject.movieinfo2.utils.network.handleApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(page: Int): Flow<DataState<PageModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getNowPlayingMovieList(page)
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getMovieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getGenreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun nowPlayingPagingDataSource() = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun popularPagingDataSource() = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun topRatedPagingDataSource() = Pager(
        pagingSourceFactory = { TopRatedPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun upcomingPagingDataSource() = Pager(
        pagingSourceFactory = { UpcomingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    fun genrePagingDataSource(genreId: String) = Pager(
        pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow
}