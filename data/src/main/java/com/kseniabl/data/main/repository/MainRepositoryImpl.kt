package com.kseniabl.data.main.repository

import android.util.Log
import com.kseniabl.data.entity.BannerModel
import com.kseniabl.data.main.cashData
import com.kseniabl.data.main.source.LocalSource
import com.kseniabl.data.main.source.RetrofitRequests
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteSource: RetrofitRequests,
    private val localSource: LocalSource,
    private val dbRepository: DeliveryDatabaseRepository
): MainDataRepository {

    /*override suspend fun getBeer(): Flow<List<BeerRequest>> = flow {
        emit(remoteSource.getBeer())
    }*/

    override fun getBanners(): List<BannerModel> =
        localSource.getBanners()

    override fun getCashBeer() = cashData(
        apiCall = {
            try {
                if (remoteSource.getBeer().isSuccessful)
                    remoteSource.getBeer().body()
                else {
                    Log.e("MainRepositoryImpl", "Loading failed: no server access")
                    null
                }
            } catch (exception: Exception) {
                Log.e("MainRepositoryImpl", "Loading failed: ${exception.message}")
                null
            }
        },
        dbCall = {
            dbRepository.getBeer()
        },
        saveData = {
            if (it != null) {
                dbRepository.clearBeerList()
                dbRepository.insertBeer(it)
            }
        }
    )
}