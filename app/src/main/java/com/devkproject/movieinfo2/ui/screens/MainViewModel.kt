package com.devkproject.movieinfo2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.movieinfo2.data.model.Genres
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.repository.MovieRepository
import com.devkproject.movieinfo2.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    val genres: MutableState<DataState<Genres>?> = mutableStateOf(null)
    val searchData: MutableState<DataState<PageModel>?> = mutableStateOf(null)

    fun genreList() {
        viewModelScope.launch {
            movieRepository.getGenreList().onEach {
                genres.value = it
            }.launchIn(viewModelScope)
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun searchApi(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey).debounce(300)
                .filter {
                    it.trim().isEmpty().not()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    movieRepository.getSearch(it)
                }.collect {
                    if (it is DataState.Success) {
                        it.data
                    }
                    searchData.value = it
                }
        }
    }
}