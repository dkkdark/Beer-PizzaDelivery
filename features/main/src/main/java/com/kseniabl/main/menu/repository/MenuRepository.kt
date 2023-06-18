package com.kseniabl.main.menu.repository

import com.kseniabl.main.menu.entity.BeerModel
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getBeer(): Flow<List<BeerModel>>
}