package com.localiza.myapplication.greeting.data

import com.localiza.myapplication.greeting.domain.Weather
import com.localiza.myapplication.greeting.domain.datasource.MoistureDataSource
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository
const val DEGREE_RAINING = 40
class WeatherRepositoryImpl(val moistureDataSource: MoistureDataSource) : WeatherRepository {

    override suspend fun getWeather(currentDegreeCelsius: Int): Weather {
        val isRaining = isRaining(currentDegreeCelsius)
        return Weather(isRaining = isRaining(currentDegreeCelsius), winterIsComing = isRaining)
    }


    private suspend fun isRaining(currentDegreeCelsius: Int) =
        ((moistureDataSource.getMoistureRatio() + currentDegreeCelsius) / 2) <= DEGREE_RAINING


}