package com.kseniabl.data.main.repository

import com.kseniabl.data.entity.BannerModel
import com.kseniabl.data.entity.BeerRequest
import kotlinx.coroutines.flow.Flow

interface MainDataRepository {

    fun getCashBeer(): Flow<List<BeerRequest>>

    fun getBanners(): List<BannerModel>
}
