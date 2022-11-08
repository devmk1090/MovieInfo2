package com.devkproject.movieinfo2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devkproject.movieinfo2.navigation.Navigation
import com.devkproject.movieinfo2.navigation.NavigationScreen
import com.devkproject.movieinfo2.navigation.currentRoute
import com.devkproject.movieinfo2.ui.component.CircularProgressBar
import com.devkproject.movieinfo2.ui.component.NavigationItem
import com.devkproject.movieinfo2.ui.component.appbar.HomeAppBar
import com.devkproject.movieinfo2.ui.theme.floatingActionBackground
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState()
    val searchProgressBar = remember { mutableStateOf(false) }

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
            when (currentRoute(navController)) {
                NavigationScreen.HOME, NavigationScreen.POPULAR, NavigationScreen.TOP_RATED, NavigationScreen.UP_COMING -> {
                    FloatingActionButton(
                        onClick = {
                            isAppBarVisible.value = false
                        },
                        backgroundColor = floatingActionBackground
                    ) {
                        Icon(Icons.Filled.Search, "", tint = Color.White)
                    }
                }
            }
        },
        bottomBar = {
            when (currentRoute(navController)) {
                NavigationScreen.HOME, NavigationScreen.POPULAR, NavigationScreen.TOP_RATED, NavigationScreen.UP_COMING -> {
                    BottomNavigationUI(navController)
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
                CircularProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
                if (isAppBarVisible.value.not()) {

                }
            }
        }
    }
}

@Composable
fun BottomNavigationUI(navController: NavController) {
    BottomNavigation {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Popular,
            NavigationItem.TopRated,
            NavigationItem.Upcoming,
        )
        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = item.title) },
                selected = currentRoute(navController) == item.route,
                icon = item.icon,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}