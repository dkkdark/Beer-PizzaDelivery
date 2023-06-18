package com.kseniabl.data.main.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kseniabl.data.entity.BeerRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Query("SELECT * FROM BeerRequest")
    fun loadBeer(): Flow<List<BeerRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(cards: List<BeerRequest>)

    @Query("DELETE FROM BeerRequest")
    fun deleteBeer()
}