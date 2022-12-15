package com.devkproject.movieinfo2.data.remote

import com.devkproject.movieinfo2.data.model.Genres
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.model.TvPageModel
import com.devkproject.movieinfo2.data.model.artist.ArtistCrew
import com.devkproject.movieinfo2.data.model.artist.ArtistDetail
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.model.video.Videos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.NOW_PLAYING_MOVIE_LIST)
    suspend fun getNowPlayingMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.POPULAR_MOVIE_LIST)
    suspend fun getPopularMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.TOP_RATED_MOVIE_LIST)
    suspend fun getTopRatedMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.UP_COMING_MOVIE_LIST)
    suspend fun getUpcomingMovieList(@Query("page") page: Int): PageModel

    @GET(ApiUrl.MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetail

    @GET(ApiUrl.GENRE_LIST)
    suspend fun genreList(): Genres

    @GET(ApiUrl.GENRE_MOVIES_BY_ID)
    suspend fun moviesGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String
    ): PageModel

    @GET(ApiUrl.SEARCH_MOVIE)
    suspend fun getSearch(@Query("query") searchKey: String): PageModel

    @GET(ApiUrl.MOVIE_VIDEO)
    suspend fun getMovieVideos(@Path("movieId") movieId: Int): Videos

    @GET(ApiUrl.RECOMMENDED_MOVIE)
    suspend fun getRecommendedMovie(@Path("movieId") movieId: Int, @Query("page") one: Int): PageModel

    @GET(ApiUrl.MOVIE_CREDIT)
    suspend fun getMovieCredit(@Path("movieId") movieId: Int): ArtistCrew

    @GET(ApiUrl.ARTIST_DETAIL)
    suspend fun getArtistDetail(@Path("personId") personId: Int): ArtistDetail

    //TV
    @GET(ApiUrl.TV_POPULAR)
    suspend fun getTvPopularList(@Query("page") page: Int): TvPageModel
}