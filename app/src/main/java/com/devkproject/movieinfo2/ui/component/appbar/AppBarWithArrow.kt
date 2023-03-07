package com.devkproject.movieinfo2.ui.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devkproject.movieinfo2.ui.theme.Purple500
import com.devkproject.movieinfo2.ui.theme.blue

@Composable
fun AppBarWithArrow(
    title: String?,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = blue,
        modifier = Modifier.height(48.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                imageVector = Icons.Filled.ArrowBack,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {
                        pressOnBack()
                    }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                text = title ?: "",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun AppBarWithArrowText() {
    AppBarWithArrow(title = "AppBarWithArrow") {

    }
}