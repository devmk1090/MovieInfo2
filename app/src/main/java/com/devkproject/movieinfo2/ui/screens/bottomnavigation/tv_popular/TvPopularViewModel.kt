package com.devkproject.movieinfo2.ui.screens.bottomnavigation.tv_popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.devkproject.movieinfo2.data.repository.TVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvPopularViewModel @Inject constructor(tvRepository: TVRepository) : ViewModel() {
    val tvPopular = tvRepository.tvPopularPagingDataSource().cachedIn(viewModelScope)
}