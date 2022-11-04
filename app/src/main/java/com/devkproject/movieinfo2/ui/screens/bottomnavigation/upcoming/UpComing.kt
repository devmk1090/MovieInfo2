package com.devkproject.movieinfo2.ui.screens.bottomnavigation.upcoming

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen


@Composable
fun Upcoming(
    navController: NavController,
) {
    val upComingViewModel = hiltViewModel<UpComingViewModel>()
    HomeScreen(
        navController = navController,
        movies = upComingViewModel.upcomingMovies
    )
}