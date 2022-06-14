package com.exxuslee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exxuslee.data.local.dao.CurrencyDao
import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.local.entities.CurrencyEntity
import com.exxuslee.data.local.entities.PriceEntity
import com.exxuslee.data.local.typeconverter.Converters

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Database(entities = [PriceEntity::class, CurrencyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PriceDatabase : RoomDatabase() {
    abstract val priceDao: PriceDao
    abstract val currencyDao: CurrencyDao
}