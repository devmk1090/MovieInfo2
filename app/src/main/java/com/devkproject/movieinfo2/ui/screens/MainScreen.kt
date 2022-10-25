package com.devkproject.movieinfo2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devkproject.movieinfo2.ui.component.appbar.HomeAppBar
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
        }
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