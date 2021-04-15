package com.example.jetpackcomposetimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposetimer.ui.screen.ShowCountDownTimer
import com.example.jetpackcomposetimer.ui.theme.JetpackComposeTimerTheme
import com.example.jetpackcomposetimer.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTimerTheme {
                MyApp(viewModel = homeViewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: HomeViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        ShowCountDownTimer(homeVM = viewModel)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {

    JetpackComposeTimerTheme {

    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    JetpackComposeTimerTheme(darkTheme = true) {

    }
}

