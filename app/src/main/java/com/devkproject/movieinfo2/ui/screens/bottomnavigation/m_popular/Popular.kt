package com.devkproject.movieinfo2.ui.screens.bottomnavigation.m_popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen

@Composable
fun Popular(
    navController: NavController
) {
    val popularViewModel = hiltViewModel<PopularViewModel>()
    HomeScreen(
        navController = navController,
        movies = popularViewModel.popularMovies
    )
}