package com.localiza.myapplication.greeting.data.datasource

import com.localiza.myapplication.greeting.data.datasource.MoistureDataSource

class MoistureDataSourceImpl : MoistureDataSource {
    override suspend fun getMoistureRatio(): Int {
        return 40
    }
}