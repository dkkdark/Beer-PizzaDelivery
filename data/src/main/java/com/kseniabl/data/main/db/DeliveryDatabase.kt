package com.kseniabl.data.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kseniabl.data.entity.BeerRequest

@Database(entities = [BeerRequest::class], version = 1)
abstract class DeliveryDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {
        const val DATABASE_NAME = "DeliveryDatabase"
    }
}