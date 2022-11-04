package com.devkproject.movieinfo2.ui.screens.bottomnavigation.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.devkproject.movieinfo2.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    val nowPlayingMovieList = movieRepository.nowPlayingPagingDataSource().cachedIn(viewModelScope)

}