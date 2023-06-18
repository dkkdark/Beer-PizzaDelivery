package com.kseniabl.data.main.repository

import com.kseniabl.data.entity.BeerRequest
import kotlinx.coroutines.flow.Flow

interface DeliveryDatabaseRepository {
    fun getBeer(): Flow<List<BeerRequest>>
    fun insertBeer(item: List<BeerRequest>)
    fun clearBeerList()
}