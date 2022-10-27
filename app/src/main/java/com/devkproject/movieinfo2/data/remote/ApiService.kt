package com.devkproject.movieinfo2.data.remote

import com.devkproject.movieinfo2.data.model.MovieItem
import com.devkproject.movieinfo2.data.model.PageModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.MOVIE_LIST)
    suspend fun getNowPlayingMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.POPULAR_MOVIE_LIST)
    suspend fun getPopularMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.TOP_RATED_MOVIE_LIST)
    suspend fun getTopRatedMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.UP_COMING_MOVIE_LIST)
    suspend fun getUpcomingMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieItem
}