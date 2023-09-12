package com.localiza.myapplication.di

import com.localiza.myapplication.greeting.data.MoistureDataSourceImpl
import com.localiza.myapplication.greeting.data.WeatherRepositoryImpl
import com.localiza.myapplication.greeting.domain.datasource.MoistureDataSource
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository

data class AppModule(
    val moistureDataSource: MoistureDataSource = MoistureDataSourceImpl(),
    val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        moistureDataSource = moistureDataSource
    ),
)