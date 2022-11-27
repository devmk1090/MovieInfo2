package com.devkproject.movieinfo2.ui.component.appbar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devkproject.movieinfo2.ui.screens.MainViewModel
import com.devkproject.movieinfo2.ui.theme.blue

@Composable
fun SearchBar(isAppBarVisible: MutableState<Boolean>, viewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    BackHandler(isAppBarVisible.value.not()) {
        isAppBarVisible.value = true
    }
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                disabledLabelColor = blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                text = it
            },
            singleLine = true,
            trailingIcon = {
                if (text.trim().isNotEmpty()) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "empty text",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                text = ""
                            }
                    )
                } else {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { }
                    )
                }
            }
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
fun SearchBarTest() {
    val isAppBarVisible = remember { mutableStateOf(true) }
    val mainViewModel = hiltViewModel<MainViewModel>()

    SearchBar(isAppBarVisible = isAppBarVisible, viewModel = mainViewModel)
}