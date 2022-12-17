package com.devkproject.movieinfo2.ui.screens.bottomnavigation.tv_popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen

@Composable
fun TvPopular(navController: NavController) {
    val tvPopularViewModel = hiltViewModel<TvPopularViewModel>()
    HomeScreen(navController = navController, movies = null, tvPopularViewModel.tvPopular)
}