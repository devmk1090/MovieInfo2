package com.devkproject.movieinfo2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devkproject.movieinfo2.ui.theme.MovieInfo2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieInfo2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainName("MovieInfo2")
                }
            }
        }
    }
}

@Composable
fun MainName(name: String) {
    Text(name)
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    MovieInfo2Theme {
        MainName("MovieInfo2")
    }
}