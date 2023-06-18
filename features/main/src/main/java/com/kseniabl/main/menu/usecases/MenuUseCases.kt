package com.kseniabl.main.menu.usecases

import com.kseniabl.main.menu.entity.BeerModel
import com.kseniabl.main.menu.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuUseCases @Inject constructor(
    private val repository: MenuRepository
) {

    suspend fun getBeerUseCase(): Flow<List<BeerModel>> {
        return repository.getBeer()
    }

}