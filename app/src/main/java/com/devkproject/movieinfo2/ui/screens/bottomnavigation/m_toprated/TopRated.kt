package com.devkproject.movieinfo2.ui.screens.bottomnavigation.m_toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen

@Composable
fun TopRated(
    navController: NavController,
) {
    val topRatedViewModel = hiltViewModel<TopRatedViewModel>()
    HomeScreen(
        navController = navController,
        movies = topRatedViewModel.topRatedMovies
    )
}