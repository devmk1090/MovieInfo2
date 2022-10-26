package com.devkproject.movieinfo2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.devkproject.movieinfo2.ui.component.appbar.HomeAppBar
import com.devkproject.movieinfo2.ui.theme.floatingActionBackground
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val coroutineScope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                title = "test",
                openDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
                openFilters = {
                    isAppBarVisible.value = false

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAppBarVisible.value = false
                },
                backgroundColor = floatingActionBackground
            ) {
                Icon(
                    Icons.Filled.Search,
                    "",
                    tint = Color.White
                )

            }
        },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {

        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}