package com.devkproject.movieinfo2.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devkproject.movieinfo2.ui.MainViewModel
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
                backgroundColor = blue,
                cursorColor = Color.Black,
                disabledLabelColor = blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                text = it
            },
            singleLine = true,
        )
    }
}

@Preview
@Composable
fun SearchBarTest() {
    val isAppBarVisible = remember { mutableStateOf(true) }
    val mainViewModel = hiltViewModel<MainViewModel>()

    SearchBar(isAppBarVisible = isAppBarVisible, viewModel = mainViewModel)
}