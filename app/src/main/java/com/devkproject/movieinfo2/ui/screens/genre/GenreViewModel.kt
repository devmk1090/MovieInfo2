package com.devkproject.movieinfo2.ui.screens.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.devkproject.movieinfo2.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    fun movieGenre(genreId: String) =
        movieRepository.genrePagingDataSource(genreId).cachedIn(viewModelScope)
}