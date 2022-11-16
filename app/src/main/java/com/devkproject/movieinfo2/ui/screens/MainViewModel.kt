package com.devkproject.movieinfo2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.movieinfo2.data.model.Genres
import com.devkproject.movieinfo2.data.repository.MovieRepository
import com.devkproject.movieinfo2.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    val genres: MutableState<DataState<Genres>?> = mutableStateOf(null)

    fun genreList() {
        viewModelScope.launch {
            movieRepository.getGenreList().onEach {
                genres.value = it
            }.launchIn(viewModelScope)
        }
    }
}