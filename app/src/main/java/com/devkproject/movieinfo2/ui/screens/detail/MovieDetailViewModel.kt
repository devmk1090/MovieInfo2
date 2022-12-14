package com.devkproject.movieinfo2.ui.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.model.artist.ArtistCrew
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.model.video.Videos
import com.devkproject.movieinfo2.data.repository.MovieRepository
import com.devkproject.movieinfo2.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetail>?> = mutableStateOf(null)
    val recommendedMovie: MutableState<DataState<PageModel>?> = mutableStateOf(null)
    val artistCrew: MutableState<DataState<ArtistCrew>?> = mutableStateOf(null)
    val videoList: MutableState<DataState<Videos>?> = mutableStateOf(null)

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieDetail(movieId).onEach {
                movieDetail.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun movieVideo(movieInt: Int) {
        viewModelScope.launch {
            movieRepository.getMovieVideo(movieInt).onEach {
                videoList.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun recommendedMovie(movieId: Int, page: Int) {
        viewModelScope.launch {
            movieRepository.getRecommendedMovie(movieId, page).onEach {
                recommendedMovie.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun movieCredit(movieInt: Int) {
        viewModelScope.launch {
            movieRepository.getMovieCredit(movieInt).onEach {
                artistCrew.value = it
            }.launchIn(viewModelScope)
        }
    }
}