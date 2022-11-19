package com.devkproject.movieinfo2.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo2.R
import com.devkproject.movieinfo2.data.model.moviedetail.MovieDetail
import com.devkproject.movieinfo2.data.remote.ApiUrl
import com.devkproject.movieinfo2.ui.component.CircularProgressBar
import com.devkproject.movieinfo2.ui.component.text.SubtitlePrimary
import com.devkproject.movieinfo2.ui.component.text.SubtitleSecondary
import com.devkproject.movieinfo2.ui.theme.backgroundColor
import com.devkproject.movieinfo2.ui.theme.textColorPrimary
import com.devkproject.movieinfo2.ui.theme.textColorSecondary
import com.devkproject.movieinfo2.utils.network.DataState

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val movieDetail = movieDetailViewModel.movieDetail

    LaunchedEffect(true) {
        movieDetailViewModel.movieDetail(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        CircularProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        movieDetail.value?.let { it ->
            Log.d("501501", it.toString())
            if (it is DataState.Success<MovieDetail>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(
                        painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(it.data.poster_path)),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                    )
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
                    }
                }
            }
        }
    }
}