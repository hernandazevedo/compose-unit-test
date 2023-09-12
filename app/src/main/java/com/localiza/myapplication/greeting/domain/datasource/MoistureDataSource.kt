package com.localiza.myapplication.greeting.domain.datasource

interface MoistureDataSource {
    suspend fun getMoistureRatio(): Int
}