package com.devkproject.movieinfo2.ui.screens.genre

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devkproject.movieinfo2.ui.component.HomeScreen

@Composable
fun GenreScreen(
    navController: NavController,
    genreId: String
) {
    val genreViewModel = hiltViewModel<GenreViewModel>()
    HomeScreen(
        navController = navController,
        movies = genreViewModel.movieGenre(genreId)
    )
}