package com.localiza.myapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        var textValue by remember {
            mutableStateOf("40")
        }

        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background) {
            Column {
                    TextField(
                        label = { Text("Temperature celsius") },
                        modifier = Modifier.defaultMinSize(minHeight = 40.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = textValue,
                        onValueChange = { newText ->
                            textValue = newText
                        }
                    )
                Button(onClick = {viewModel.checkWeather(textValue.toInt())}) {
                    Text("Check Weather")
                }
                Greeting(name = "Android", state = state.value)
            }
        }
    }
}