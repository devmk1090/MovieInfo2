package com.devkproject.movieinfo2.ui.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun AppBarOnlyArrow(pressOnBack: () -> Unit) {
    Image(
        imageVector = Icons.Filled.ArrowBack,
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                pressOnBack()
            }
    )
}