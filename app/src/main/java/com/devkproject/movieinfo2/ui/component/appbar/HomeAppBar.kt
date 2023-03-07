package com.devkproject.movieinfo2.ui.component.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.devkproject.movieinfo2.ui.theme.blue

@Composable
fun HomeAppBar(title: String, openDrawer: () -> Unit, openFilters: () -> Unit) {
    TopAppBar(
        backgroundColor = blue,
        title = {
            Text(text = title,
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = openFilters) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeAppBarTest() {
    HomeAppBar(title = "MovieInfo", openDrawer = { }, openFilters = { })
}

//Spacer(modifier = Modifier.weight(4f))
//
//Image(
//imageVector = Icons.Filled.Search,
//colorFilter = ColorFilter.tint(Color.White),
//contentDescription = null,
//modifier = Modifier
//.align(Alignment.CenterVertically)
//.padding(end = 10.dp)
//.clickable {
//
//}
//)
