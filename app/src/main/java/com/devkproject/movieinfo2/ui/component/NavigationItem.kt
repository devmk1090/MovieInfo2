package com.devkproject.movieinfo2.ui.component

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devkproject.movieinfo2.navigation.NavigationScreen

sealed class NavigationItem(
    var route: String,
    var icon: @Composable () -> Unit,
    var title: String
) {
    object Home : NavigationItem(NavigationScreen.HOME, {
        Icon(
            Icons.Filled.Home,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    }, "Home")
}
