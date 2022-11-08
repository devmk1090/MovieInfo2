package com.devkproject.movieinfo2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.nowplaying.NowPlaying
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.popular.Popular
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.toprated.TopRated
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.upcoming.Upcoming

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController, startDestination = "home", modifier) {
        composable(NavigationScreen.HOME) {
            NowPlaying(
                navController = navController,
            )
        }
        composable(NavigationScreen.POPULAR) {
            Popular(
                navController = navController
            )
        }
        composable(NavigationScreen.TOP_RATED) {
            TopRated(
                navController = navController
            )
        }
        composable(NavigationScreen.UP_COMING) {
            Upcoming(
                navController = navController
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}