package com.devkproject.movieinfo2.ui.screens.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devkproject.movieinfo2.ui.screens.MainScreen
import com.devkproject.movieinfo2.ui.theme.MovieInfo2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { splashViewModel.isLoading.value }
        }
        setContent {
            MovieInfo2Theme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    MovieInfo2Theme {
        MainScreen()
    }
}