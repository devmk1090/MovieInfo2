package com.devkproject.movieinfo2.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devkproject.movieinfo2.R

@Composable
fun ExitAlertDialog(navController: NavController, cancel: (isOpen: Boolean) -> Unit, ok: () -> Unit) {

    val openDialog = remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(
                text = stringResource(R.string.text_close_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        text = {
            Text(
                text = stringResource(R.string.text_close_content),
                fontSize = 16.sp
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    ok()
                }) {
                Text(
                    stringResource(R.string.text_close_yes),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = Color.Black)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    cancel(false)
                }) {
                Text(
                    stringResource(R.string.text_close_no),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = Color.Black)
                )
            }
        },
    )
}

@Preview
@Composable
fun ExitAlertDialogText() {
    val navController = rememberNavController()
    val openDialog = remember { mutableStateOf(false) }

    ExitAlertDialog(navController, {
        openDialog.value = it
    }, {
    })
}