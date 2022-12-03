package com.devkproject.movieinfo2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devkproject.movieinfo2.data.model.Genres
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.model.artist.ArtistCrew
import com.devkproject.movieinfo2.data.model.artist.ArtistDetail
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.remote.ApiService
import com.devkproject.movieinfo2.data.remote.paging.*
import com.devkproject.movieinfo2.utils.network.DataState
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

    suspend fun getSearch(searchKey: String): Flow<DataState<PageModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getSearch(searchKey)
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getRecommendedMovie(movieId: Int, page: Int): Flow<DataState<PageModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getRecommendedMovie(movieId, page)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getMovieCredit(movieId: Int): Flow<DataState<ArtistCrew>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.getMovieCredit(movieId)
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getArtistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val artistDetailResult = apiService.getArtistDetail(personId)
            emit(DataState.Success(artistDetailResult))
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