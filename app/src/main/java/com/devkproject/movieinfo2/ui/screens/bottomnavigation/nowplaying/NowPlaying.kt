package com.devkproject.movieinfo2.ui.screens.bottomnavigation.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen

@Composable
fun NowPlaying(
    navController: NavController,
) {
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(
        navController = navController,
        movies = nowPlayingViewModel.nowPlayingMovieList
    )
}