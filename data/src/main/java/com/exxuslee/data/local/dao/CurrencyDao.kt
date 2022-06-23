package com.exxuslee.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exxuslee.data.local.entities.CurrencyEntity

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCurrency(Currency: CurrencyEntity)

    @Query("SELECT * FROM currency_table")
    suspend fun getCurrency(): List<CurrencyEntity>?

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrency()

}