package com.localiza.myapplication.greeting.domain.repository

import com.localiza.myapplication.greeting.domain.Weather

interface WeatherRepository {

    suspend fun getWeather(currentDegreeCelsius: Int) : Weather
}