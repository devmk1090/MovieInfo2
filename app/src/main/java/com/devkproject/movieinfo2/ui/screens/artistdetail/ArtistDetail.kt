package com.devkproject.movieinfo2.ui.screens.artistdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo2.R
import com.devkproject.movieinfo2.data.model.artist.ArtistDetail
import com.devkproject.movieinfo2.data.remote.ApiUrl
import com.devkproject.movieinfo2.ui.component.CircularProgressBar
import com.devkproject.movieinfo2.ui.component.appbar.AppBarOnlyArrow
import com.devkproject.movieinfo2.ui.component.text.BioGraphyText
import com.devkproject.movieinfo2.ui.theme.backgroundColor
import com.devkproject.movieinfo2.ui.theme.cornerRadius10
import com.devkproject.movieinfo2.ui.theme.textColorPrimary
import com.devkproject.movieinfo2.ui.theme.textColorSecondary
import com.devkproject.movieinfo2.utils.genderInString
import com.devkproject.movieinfo2.utils.network.DataState
import com.devkproject.movieinfo2.utils.pagingLoadingState

@Composable
fun ArtistDetail(navController: NavController, personId: Int) {
    val artistDetailViewModel= hiltViewModel<ArtistDetailViewModel>()
    val artistDetail = artistDetailViewModel.artistDetail
    val progressBar = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        artistDetailViewModel.artistDetail(personId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        CircularProgressBar(isDisplayed = progressBar.value, 0.4f)

        artistDetail.value.let {
            if (it is DataState.Success<ArtistDetail>) {
                Row {
                    Box {
                        Image(
                            painter = rememberImagePainter(ApiUrl.POSTER_URL.plus(it.data.profilePath)),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .height(250.dp)
                                .width(190.dp)
                                .cornerRadius10()
                        )
                        AppBarOnlyArrow(color = Color.Black) {
                            navController.popBackStack()
                        }
                    }

                    Column {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = it.data.name,
                            color = textColorPrimary,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Medium
                        )
                        PersonalInfo(stringResource(R.string.know_for), it.data.knownForDepartment)
                        PersonalInfo(stringResource(R.string.gender), it.data.gender.genderInString())
                        PersonalInfo(stringResource(R.string.birth_day), it.data.birthday)
                        PersonalInfo(stringResource(R.string.place_of_birth), it.data.placeOfBirth)
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(R.string.biography),
                    color = textColorSecondary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
                BioGraphyText(text = it.data.biography)
            }
        }
    }
    artistDetail.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun PersonalInfo(title: String, info: String) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)) {
        Text(
            text = title,
            color = textColorSecondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info,
            color = textColorPrimary,
            fontSize = 16.sp
        )
    }
}