package com.exxuslee.data.local.dao

import androidx.room.*
import com.exxuslee.data.local.entities.CurrencyEntity

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Dao
interface CurrencyDao {

    @Query("UPDATE currency_table SET base = :base, `check` = :check WHERE xxx = :xxx")
    fun updateCurrency(xxx: String, base: Boolean, check: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currency_table")
    suspend fun getCurrency(): List<CurrencyEntity>?

    @Query("DELETE FROM currency_table")
    suspend fun deleteAllCurrency()

}