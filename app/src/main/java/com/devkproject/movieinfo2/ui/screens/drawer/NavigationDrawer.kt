package com.devkproject.movieinfo2.ui.screens.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devkproject.movieinfo2.data.model.moviedetail.Genre
import com.devkproject.movieinfo2.navigation.NavigationScreen
import com.devkproject.movieinfo2.navigation.currentRoute

@Composable
fun DrawerUI(
    navController: NavController,
    genres: List<Genre>,
    closeDrawer: (genreName: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(items = genres, itemContent = { item ->
            DrawerItem(
                item = item,
                selected = currentRoute(navController) == "",
                onItemClick = {
                    navController.navigate(NavigationScreen.NAVIGATION_DRAWER.plus("/${it.id}")) {
                        launchSingleTop = true
                    }
                    //Close
                    closeDrawer(it.name)
                })
        })
    }
}

@Composable
fun DrawerItem(item: Genre, selected: Boolean, onItemClick: (Genre) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .padding(start = 10.dp)
    ) {
        Icon(
            Icons.Outlined.Movie, "", modifier = Modifier
                .height(24.dp)
                .padding(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.name,
            fontSize = 18.sp
        )
    }
}