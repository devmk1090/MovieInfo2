package com.devkproject.movieinfo2.ui.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.devkproject.movieinfo2.data.model.MovieItem
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    movies: Flow<PagingData<MovieItem>>
) {
    val activity = (LocalContext.current as? Activity)
}