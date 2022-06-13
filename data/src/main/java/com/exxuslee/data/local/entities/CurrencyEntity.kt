package com.exxuslee.data.local.entities

import androidx.collection.ArrayMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxuslee.data.utils.Constants

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Entity(tableName = Constants.CURRENCY_TABLE)
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "symbols") val symbols: ArrayMap<String, String>,
    @ColumnInfo(name = "favorite") val favorite: ArrayList<Boolean>,
    @ColumnInfo(name = "base") val base: String,
)