package com.devkproject.movieinfo2.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.devkproject.movieinfo2.ui.theme.bioGrapyText

@Composable
fun BioGraphyText(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bioGrapyText
    )
}
