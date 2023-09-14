package com.localiza.myapplication.di

import com.localiza.myapplication.greeting.data.datasource.MoistureDataSourceImpl
import com.localiza.myapplication.greeting.data.repository.WeatherRepositoryImpl
import com.localiza.myapplication.greeting.data.datasource.MoistureDataSource
import com.localiza.myapplication.greeting.domain.repository.WeatherRepository

data class AppModule(
    val moistureDataSource: MoistureDataSource = MoistureDataSourceImpl(),
    val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        moistureDataSource = moistureDataSource
    ),
)