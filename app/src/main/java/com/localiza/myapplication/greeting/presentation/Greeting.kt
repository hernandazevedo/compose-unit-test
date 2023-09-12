package com.localiza.myapplication.greeting.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun Greeting(name: String, state: GreetingViewState) {
    when(state) {
        is GreetingViewState.SunshineViewState -> {
            Text(text = "Hello $name! the season is ${state.seasonName} , and its not raining")
        }
        is GreetingViewState.RainyViewState -> {
            Text(text = "Hello $name! the season is ${state.seasonName} , and its raining")
        }
        is GreetingViewState.DefaultState -> {
            Text(text = "Hello $name! please click to check weather")
        }
    }

}