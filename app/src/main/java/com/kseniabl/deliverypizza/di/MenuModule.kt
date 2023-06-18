package com.kseniabl.deliverypizza.di

import com.kseniabl.deliverypizza.domain.MainRepository
import com.kseniabl.deliverypizza.domain.adapter.MainAdapter
import com.kseniabl.deliverypizza.domain.adapter.MenuRepositoriesAdapter
import com.kseniabl.main.menu.repository.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdaptersModule {

    @Binds
    fun bindMenuRepository(
        repository: MenuRepositoriesAdapter
    ): MenuRepository

    @Binds
    fun bindMainRepository(
        repository: MainAdapter
    ): MainRepository

}