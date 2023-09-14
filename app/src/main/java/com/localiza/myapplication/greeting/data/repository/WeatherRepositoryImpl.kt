package com.localiza.myapplication.greeting.data.repository

import com.localiza.myapplication.greeting.domain.Weather
import com.localiza.myapplication.greeting.data.datasource.MoistureDataSource
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository
const val DEGREE_RAINING = 40
class WeatherRepositoryImpl(val moistureDataSource: MoistureDataSource) : WeatherRepository {

    override suspend fun getWeather(currentDegreeCelsius: Int): Weather {
        val isRaining = isRaining(currentDegreeCelsius)
        val mediumTemperature = calculateMediumTemperature(currentDegreeCelsius)
        return Weather(isRaining = isRaining,
            winterIsComing = isRaining,
            mediumTemperature = mediumTemperature)
    }


    suspend fun isRaining(currentDegreeCelsius: Int) =
        calculateMediumTemperature(currentDegreeCelsius) <= DEGREE_RAINING

    suspend fun calculateMediumTemperature(currentDegreeCelsius: Int) =
        ((moistureDataSource.getMoistureRatio() + currentDegreeCelsius) / 2).toDouble()

}