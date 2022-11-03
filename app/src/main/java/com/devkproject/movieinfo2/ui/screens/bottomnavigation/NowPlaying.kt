package com.devkproject.movieinfo2.ui.screens.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NowPlaying(
    navController: NavController,
) {
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    
}