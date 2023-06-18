package com.kseniabl.data.main.di

import android.content.Context
import androidx.room.Room
import com.kseniabl.data.main.db.BeerDao
import com.kseniabl.data.main.db.DeliveryDatabase
import com.kseniabl.data.main.db.DeliveryDatabase.Companion.DATABASE_NAME
import com.kseniabl.data.main.repository.DeliveryDatabaseRepository
import com.kseniabl.data.main.repository.DeliveryDatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeliveryDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DeliveryDatabase =
        Room.databaseBuilder(
            context,
            DeliveryDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(database: DeliveryDatabase): BeerDao = database.beerDao()

    @Provides
    @Singleton
    fun provideDatabaseRepository(beerDao: BeerDao): DeliveryDatabaseRepository =
        DeliveryDatabaseRepositoryImpl(beerDao)
}