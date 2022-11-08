package com.devkproject.movieinfo2.ui.screens.bottomnavigation.upcoming


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.devkproject.movieinfo2.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    val upcomingMovies = movieRepository.upcomingPagingDataSource().cachedIn(viewModelScope)
}