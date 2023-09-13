package com.localiza.myapplication.greeting.data

import com.localiza.myapplication.greeting.domain.datasource.MoistureDataSource

class MoistureDataSourceImpl : MoistureDataSource {
    override suspend fun getMoistureRatio(): Int {
        return 40
    }
}