package com.devkproject.movieinfo2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devkproject.movieinfo2.data.remote.ApiService
import com.devkproject.movieinfo2.data.remote.paging.NowPlayingPagingDataSource
import com.devkproject.movieinfo2.data.remote.paging.PopularPagingDataSource
import com.devkproject.movieinfo2.data.remote.paging.TopRatedPagingDataSource
import com.devkproject.movieinfo2.data.remote.paging.UpcomingPagingDataSource
import com.devkproject.movieinfo2.utils.network.handleApiResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(page: Int) = handleApiResponse { apiService.getNowPlayingMovieList(page) }

    suspend fun getMovieDetail(movieId: Int) = handleApiResponse { apiService.getMovieDetail(movieId) }

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
}