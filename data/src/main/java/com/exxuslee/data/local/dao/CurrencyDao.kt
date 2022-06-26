package com.exxuslee.data.local.dao

import androidx.room.*
import com.exxuslee.data.local.entities.CurrencyEntity

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Dao
interface CurrencyDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrency(Currency: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(Currency: CurrencyEntity)

    @Query("SELECT * FROM currency_table")
    suspend fun getCurrency(): List<CurrencyEntity>?

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrency()

}