package com.localiza.myapplication.greeting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class GreetingViewState {
    object DefaultState: GreetingViewState()
    data class SunshineViewState(val seasonName: String) : GreetingViewState()
    data class RainyViewState(val seasonName: String, val winterIsComing: Boolean) : GreetingViewState()
}

class GreetingViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow<GreetingViewState>(GreetingViewState.DefaultState)
    val state: StateFlow<GreetingViewState> get() = _state

    companion object {
        fun provideFactory(
            weatherRepository: WeatherRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GreetingViewModel(
                    weatherRepository = weatherRepository
                ) as T
            }
        }
    }

    fun checkWeather(temperature: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            val weather = weatherRepository.getWeather(currentDegreeCelsius = temperature)
            val winterIsComing = weather.winterIsComing
            if(weather.isRaining) {
                _state.tryEmit(GreetingViewState.RainyViewState(
                    seasonName = "Winter",
                    winterIsComing = winterIsComing
                ))
            } else {
                _state.tryEmit(GreetingViewState.SunshineViewState(
                    seasonName = "Summer",
                ))
            }
        }
}