package com.exxuslee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exxuslee.data.local.dao.PriceDao
import com.exxuslee.data.local.entities.PriceEntity

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Database(entities = [PriceEntity::class],version = 1,exportSchema = false)
abstract class PriceDatabase : RoomDatabase(){
    abstract val priceDao: PriceDao
}