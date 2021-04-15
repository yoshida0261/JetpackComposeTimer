package com.example.jetpackcomposetimer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.reflect.KProperty

class HomeViewModel : ViewModel(){

    var isTimerRuning: Boolean by mutableStateOf(true)
    var timeRemaining: Long by mutableStateOf(60000L)
    //var timeRemaining: Long by mutableStateOf(0L)
    //    var isTimerRunning: Boolean by mutableStateOf(true)


    fun clearTimer(){
        //timeRemaining = 60
    }

}


