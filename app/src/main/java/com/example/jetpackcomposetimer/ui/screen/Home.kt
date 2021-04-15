package com.example.jetpackcomposetimer.ui.screen


import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetimer.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.max

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShowCountDownTimer(homeVM: HomeViewModel) {

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            // Enters by sliding in from offset -fullHeight to 0.
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding out from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        if (!homeVM.isTimerRuning) {
            resetDialog(homeVM = homeVM)
        }
        LaunchedEffect(key1 = "Timer") {
            while (isActive) {
                delay(1000)
                if (isActive) {
                    homeVM.timeRemaining = homeVM.timeRemaining - 1000
                    Log.d("test", homeVM.timeRemaining.toString())
                }
            }
        }


        val target = max(
            homeVM.timeRemaining.toFloat() - 1000,
            0f
        ) / 60000


        val progress by animateFloatAsState(
            targetValue = target,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            CircularProgressIndicator(
                progress = 1F,
                color = Color.Yellow,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                strokeWidth = 8.dp
            )
            if (progress >= 0) {
                CircularProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    color = Color.Black,
                    strokeWidth = 8.dp,
                )
            }


        }
    }


}

@Composable
fun resetDialog(homeVM: HomeViewModel) {
    AlertDialog(
        onDismissRequest = {
            // 終了時の処理 todo
        },
        title = {
            Text(
                text = "Time Up",
                style = MaterialTheme.typography.h4
            )
        },
        confirmButton = {

        },
        dismissButton = {
            Button(
                onClick = {
                    homeVM.isTimerRuning = !homeVM.isTimerRuning
                }
            ) {
                Text("OK")
            }
        }
    )
}

