package com.devkproject.movieinfo2.ui.screens.artistdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.movieinfo2.data.model.artist.ArtistDetail
import com.devkproject.movieinfo2.data.repository.MovieRepository
import com.devkproject.movieinfo2.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    val artistDetail: MutableState<DataState<ArtistDetail>?> = mutableStateOf(null)

    fun artistDetail(personId: Int) {
        viewModelScope.launch {
            movieRepository.getArtistDetail(personId).onEach {
                artistDetail.value = it
            }.launchIn(viewModelScope)
        }
    }
}