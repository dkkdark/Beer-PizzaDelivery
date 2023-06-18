package com.kseniabl.deliverypizza.domain.adapter

import com.kseniabl.data.main.repository.MainRepositoryImpl
import com.kseniabl.main.menu.entity.BeerModel
import com.kseniabl.main.menu.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MenuRepositoriesAdapter @Inject constructor(
    private val repositoryImpl: MainRepositoryImpl
): MenuRepository {

    override suspend fun getBeer(): Flow<List<BeerModel>> {
        return repositoryImpl.getCashBeer().map { list ->
            list.filter { it.imageUrl != null }
            list.map { BeerModel(it.id, it.name, it.description, it.imageUrl!!) }
        }
    }
}