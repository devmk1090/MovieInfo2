package com.devkproject.movieinfo2.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devkproject.movieinfo2.ui.theme.Purple500

@Composable
fun HomeAppBar(title: String, openDrawer: () -> Unit, openFilters: () -> Unit) {
    TopAppBar(
        backgroundColor = Purple500,
        title = {
            Text(text = title,
            style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = {

        },
    )
}

@Preview
@Composable
fun Test() {
    HomeAppBar(title = "MovieInfo", openDrawer = { /*TODO*/ }, openFilters = { })
}