package com.localiza.myapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.localiza.myapplication.di.AppModule
import com.localiza.myapplication.greeting.presentation.Greeting
import com.localiza.myapplication.greeting.presentation.GreetingViewModel
import com.localiza.myapplication.greeting.presentation.GreetingViewState
import com.localiza.myapplication.ui.theme.MyApplicationTheme

@Composable
fun App(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appModule: AppModule,
) {
    MyApplicationTheme(darkTheme = darkTheme) {
        val viewModel = viewModel<GreetingViewModel>(
            factory = GreetingViewModel.provideFactory(appModule.weatherRepository)
        )

        val state = viewModel.state.collectAsState(
            GreetingViewState.DefaultState)

        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            Column() {
                Button(onClick = {viewModel.checkWeather(40)}) {
                    Text("Check Weather")
                }
                Greeting(name = "Android", state = state.value)
            }
        }
    }
}