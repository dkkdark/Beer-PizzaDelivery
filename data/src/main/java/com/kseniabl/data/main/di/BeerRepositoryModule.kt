package com.kseniabl.data.main.di

import com.kseniabl.data.main.repository.MainDataRepository
import com.kseniabl.data.main.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BeerRepositoryModule {

    @Binds
    @Singleton
    fun bindCartRepository(cartDataRepository: MainRepositoryImpl):
            MainDataRepository

}