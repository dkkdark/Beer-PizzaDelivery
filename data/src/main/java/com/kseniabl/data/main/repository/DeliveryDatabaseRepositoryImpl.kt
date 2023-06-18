package com.kseniabl.data.main.repository

import com.kseniabl.data.entity.BeerRequest
import com.kseniabl.data.main.db.BeerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryDatabaseRepositoryImpl @Inject constructor(
    private val dao: BeerDao
): DeliveryDatabaseRepository {

    override fun getBeer(): Flow<List<BeerRequest>> {
        return dao.loadBeer()
    }

    override fun insertBeer(item: List<BeerRequest>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertBeer(item)
        }
    }

    override fun clearBeerList() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteBeer()
        }
    }
}