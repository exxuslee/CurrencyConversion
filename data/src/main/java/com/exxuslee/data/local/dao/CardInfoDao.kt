package com.exxuslee.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exxuslee.data.local.entities.PriceEntity

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Dao
interface PriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePrice(Price: PriceEntity)

    @Query("SELECT * FROM card_info_table WHERE base=:base LIMIT 1")
    suspend fun getPrice(base: String): PriceEntity?

    @Query("DELETE FROM card_info_table")
    suspend fun deleteAllPrice()

}