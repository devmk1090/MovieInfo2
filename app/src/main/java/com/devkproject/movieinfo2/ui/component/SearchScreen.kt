package com.devkproject.movieinfo2.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.devkproject.movieinfo2.data.model.PageModel
import com.devkproject.movieinfo2.data.remote.ApiUrl
import com.devkproject.movieinfo2.navigation.NavigationScreen
import com.devkproject.movieinfo2.ui.theme.backgroundColor
import com.devkproject.movieinfo2.ui.theme.textColorPrimary
import com.devkproject.movieinfo2.ui.theme.textColorSecondary
import com.devkproject.movieinfo2.utils.network.DataState

@Composable
fun SearchUI(navController: NavController, searchData: MutableState<DataState<PageModel>?>, itemClick:() -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp)
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 56.dp)
            .background(color = backgroundColor)
    ) {
        searchData.value?.let {
            if (it is DataState.Success<PageModel>) {
                items(items = it.data.results, itemContent = { item ->
                    Row(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable {
                                itemClick.invoke()
                                navController.navigate(
                                    NavigationScreen.MovieDetail.MOVIE_DETAIL.plus("/${item.id}")
                                )
                            }
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                ApiUrl.POSTER_URL.plus(
                                    item.posterPath
                                )
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .width(80.dp)
                        )
                        Column {
                            Text(
                                text = item.title,
                                color = textColorPrimary,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = item.releaseDate,
                                color = textColorSecondary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                            )
                            Text(
                                text = "${item.voteAverage}",
                                color = textColorSecondary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                            )
                        }
                    }
                })
            }
        }
    }
}

@Composable
@Preview
fun SearchUIPreview() {

}

//Success(data=MovieDetail(backdrop_path=/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg,
//belongs_to_collection=null,
//budget=200000000,
//genres=[
//Genre(id=28,
//name=Action),
//Genre(id=14,
//name=Fantasy),
//Genre(id=878,
//name=ScienceFiction)
//],
//homepage=https: //www.dc.com/BlackAdam,
//id=436270,
//imdb_id=tt6443346,
//original_language=en,
//original_title=BlackAdam,
//overview=Nearly5,
//000yearsafterhewasbestowedwiththealmightypowersoftheEgyptiangods—andimprisonedjustasquickly—BlackAdamisfreedfromhisearthlytomb,
//readytounleashhisuniqueformofjusticeonthemodernworld.,
//popularity=16093.223,
//poster_path=/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg,
//production_companies=[
//ProductionCompany(id=12,
//logo_path=/iaYpEp3LQmb8AfAtmTvpqd4149c.png,
//name=NewLineCinema,
//origin_country=US),
//ProductionCompany(id=34081,
//logo_path=null,
//name=FlynnPictureCompany,
//origin_country=US),
//ProductionCompany(id=73669,
//logo_path=/7tmSGstK3KwgcDIuBYLTAayjit9.png,
//name=SevenBucksProductions,
//origin_country=US),
//ProductionCompany(id=128064,
//logo_path=/13F3Jf7EFAcREU0xzZqJnVnyGXu.png,
//name=DCFilms,
//origin_country=US)
//],
//production_countries=[
//ProductionCountry(iso_3166_1=US,
//name=UnitedStatesofAmerica)
//],
//release_date=2022-10-19,
//revenue=368000000,
//runtime=125,
//spoken_languages=[
//SpokenLanguage(english_name=Danish,
//iso_639_1=da,
//name=Dansk),
//SpokenLanguage(english_name=English,
//iso_639_1=en,
//name=English)
//],
//status=Released,
//tagline=Theworldneededahero.ItgotBlackAdam.,
//title=BlackAdam,
//video=false,
//vote_average=7.234,
//vote_count=1836))