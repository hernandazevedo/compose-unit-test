package com.localiza.myapplication.greeting.data.datasource

interface MoistureDataSource {
    suspend fun getMoistureRatio(): Int
}