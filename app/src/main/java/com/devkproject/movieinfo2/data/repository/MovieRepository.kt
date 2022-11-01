package com.devkproject.movieinfo2.data.repository

import com.devkproject.movieinfo2.data.remote.ApiService
import com.devkproject.movieinfo2.utils.network.handleApiResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(page: Int) = handleApiResponse { apiService.getNowPlayingMovieList(page) }

    suspend fun getMovieDetail(movieId: Int) = handleApiResponse { apiService.getMovieDetail(movieId) }
}