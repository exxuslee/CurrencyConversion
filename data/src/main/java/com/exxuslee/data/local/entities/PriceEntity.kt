package com.exxuslee.data.local.entities

import androidx.collection.ArrayMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxuslee.data.utils.Constants.PRICE_TABLE_NAME

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Entity(tableName = PRICE_TABLE_NAME)
data class PriceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "base") val base: String? = "",
    @ColumnInfo(name = "date") val date: String? = "",
    @ColumnInfo(name = "rates") val rates: ArrayMap<String, Double>
)
