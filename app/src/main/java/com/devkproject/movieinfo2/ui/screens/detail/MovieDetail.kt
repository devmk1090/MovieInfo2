package com.devkproject.movieinfo2.ui.screens.detail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo2.R
import com.devkproject.movieinfo2.data.model.MovieItem
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.model.artist.ArtistCrew
import com.devkproject.movieinfo2.data.model.artist.Cast
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.model.video.VideoItems
import com.devkproject.movieinfo2.data.model.video.Videos
import com.devkproject.movieinfo2.data.remote.ApiUrl
import com.devkproject.movieinfo2.navigation.NavigationScreen
import com.devkproject.movieinfo2.ui.component.CircularProgressBar
import com.devkproject.movieinfo2.ui.component.appbar.AppBarOnlyArrow
import com.devkproject.movieinfo2.ui.component.text.SubtitlePrimary
import com.devkproject.movieinfo2.ui.component.text.SubtitleSecondary
import com.devkproject.movieinfo2.ui.theme.*
import com.devkproject.movieinfo2.utils.hourMinutes
import com.devkproject.movieinfo2.utils.network.DataState
import com.devkproject.movieinfo2.utils.pagingLoadingState

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {

    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val movieDetail = movieDetailViewModel.movieDetail
    val recommendedMovie = movieDetailViewModel.recommendedMovie
    val artistCrew = movieDetailViewModel.artistCrew
    val movieVideo = movieDetailViewModel.videoList

    LaunchedEffect(true) {
        movieDetailViewModel.movieDetail(movieId)
        movieDetailViewModel.recommendedMovie(movieId, 1)
        movieDetailViewModel.movieCredit(movieId)
        movieDetailViewModel.movieVideo(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        CircularProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetail>) {
                LazyColumn(Modifier.fillMaxSize(), lazyListState) {
                    item {
                        Box {
                            Image(
                                painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(it.data.poster_path)),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(600.dp)
                            )
                            AppBarOnlyArrow {
                                navController.popBackStack()
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 15.dp, end = 15.dp)
                        ) {
                            Text(
                                text = it.data.title,
                                modifier = Modifier.padding(top = 10.dp),
                                color = textColorPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W700,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis //..
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp, top = 10.dp)
                            ) {
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(text = it.data.original_language)
                                    SubtitleSecondary(text = stringResource(R.string.language))
                                }
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(text = it.data.vote_average.toString(),)
                                    SubtitleSecondary(text = stringResource(R.string.rating))
                                }
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(text = it.data.runtime.hourMinutes())
                                    SubtitleSecondary(text = stringResource(R.string.duration))
                                }
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(text = it.data.release_date)
                                    SubtitleSecondary(text = stringResource(R.string.release_date))
                                }
                            }
                            Text(
                                text = stringResource(R.string.description),
                                color = textColorPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = it.data.overview,
                                color = textColorSecondary,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            recommendedMovie.value?.let {
                                if (it is DataState.Success<PageModel>) {
                                    RecommendedMovie(navController, it.data.results)
                                }
                            }
                            artistCrew.value?.let {
                                if (it is DataState.Success<ArtistCrew>) {
                                    ArtistAndCrew(navController, it.data.cast)
                                }
                            }
                            movieVideo.value?.let {
                                if (it is DataState.Success<Videos>) {
                                    AddVideo(navController, it.data.results)
                                    Log.d("501501", it.data.results.toString())
                                }
                            }

                    }

                    }

                }
            }
        }
        recommendedMovie.pagingLoadingState {
            progressBar.value = it
        }
        movieDetail.pagingLoadingState {
            progressBar.value = it
        }
    }
}

@Composable
fun AddVideo(navController: NavController?, videoList: List<VideoItems>) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.video),
            color = textColorPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(videoList, itemContent = { item ->
                ConstraintLayout(
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp)
                        .clickable {
                            val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ApiUrl.getYoutubeVideoPath(item.key)))
                            context.startActivity(playVideoIntent)
                        }
                ) {

                }
            })
        }
    }
}

@Composable
fun RecommendedMovie(navController: NavController?, recommendedMovie: List<MovieItem>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.similar),
            color = textColorPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(recommendedMovie, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                ) {
                    Image(
                        painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(item.posterPath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(190.dp)
                            .width(140.dp)
                            .cornerRadius10()
                            .clickable {
                                navController?.navigate(
                                    NavigationScreen.MovieDetail.MOVIE_DETAIL.plus(
                                        "/${item.id}"
                                    )
                                )
                            }
                    )
                }
            })
        }
    }
}

@Composable
fun ArtistAndCrew(navController: NavController?, cast: List<Cast>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.cast),
            color = textColorPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(cast, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),

                ) {
                    Image(
                        painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(item.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(120.dp)
                            .width(80.dp)
                            .cornerRadius10()
                            .clickable {
                                navController?.navigate(
                                    NavigationScreen.ArtistDetail.ARTIST_DETAIL.plus(
                                        "/${item.id}"
                                    )
                                )
                            }

                    )
                    SubtitleSecondary(text = item.name)
                }
            })
        }
    }
}