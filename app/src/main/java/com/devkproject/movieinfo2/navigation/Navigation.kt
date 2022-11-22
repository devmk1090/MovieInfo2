package com.devkproject.movieinfo2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.devkproject.movieinfo2.R
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.nowplaying.NowPlaying
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.popular.Popular
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.toprated.TopRated
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.upcoming.Upcoming
import com.devkproject.movieinfo2.ui.screens.detail.MovieDetail

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
        composable(
            NavigationScreen.MovieDetail.MOVIE_DETAIL.plus(NavigationScreen.MovieDetail.MOVIE_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationScreen.MovieDetail.MOVIE_ITEM) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId = it.arguments?.getInt(NavigationScreen.MovieDetail.MOVIE_ITEM)
            if (movieId != null) {
                MovieDetail(navController = navController, movieId = movieId)
            }
        }
        composable(
            NavigationScreen.ArtistDetail.ARTIST_DETAIL.plus(NavigationScreen.ArtistDetail.ARTIST_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationScreen.ArtistDetail.ARTIST_ID) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.artist_detail)
            val artistId = it.arguments?.getInt(NavigationScreen.ArtistDetail.ARTIST_ID)
            if (artistId != null) {
                //TODO
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}