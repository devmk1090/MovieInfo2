package com.devkproject.movieinfo2.ui.component

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo2.data.model.MovieItem
import com.devkproject.movieinfo2.data.remote.ApiUrl
import com.devkproject.movieinfo2.navigation.NavigationScreen
import com.devkproject.movieinfo2.navigation.currentRoute
import com.devkproject.movieinfo2.ui.screens.bottomnavigation.nowplaying.NowPlayingViewModel
import com.devkproject.movieinfo2.ui.theme.backgroundColor
import com.devkproject.movieinfo2.utils.items
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavController,
    movies: Flow<PagingData<MovieItem>>
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val movieItems: LazyPagingItems<MovieItem> = movies.collectAsLazyPagingItems()

    BackHandler(enabled = (currentRoute(navController) == NavigationScreen.HOME)) {
        openDialog.value = true
    }
    Column(modifier = Modifier.background(backgroundColor)) {
        Column(modifier = Modifier.background(backgroundColor)) {
            CircularProgressBar(isDisplayed = progressBar.value, 0.4f)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
                content = {
                    items(movieItems) { item ->
                        item?.let {
                            MovieItemView(item, navController)
                        }
                    }
                })
        }
    }
}

@Composable
fun MovieItemView(item: MovieItem, navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        Image(
            painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(item.posterPath)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()

    HomeScreen(navController, movies = nowPlayViewModel.nowPlayingMovieList)
}