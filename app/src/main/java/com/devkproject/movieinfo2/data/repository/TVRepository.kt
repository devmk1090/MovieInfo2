package com.devkproject.movieinfo2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devkproject.movieinfo2.data.remote.ApiService
import com.devkproject.movieinfo2.data.remote.paging.TvPopularPagingDataSource
import javax.inject.Inject

class TVRepository @Inject constructor(private val apiService: ApiService) {

    fun tvPopularPagingDataSource() = Pager(
        pagingSourceFactory = { TvPopularPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow
}