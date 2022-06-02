package com.exxuslee.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxuslee.data.utils.Constants.PRICE_TABLE_NAME

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Entity(tableName = PRICE_TABLE_NAME)
data class PriceEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val base: String = "",
    val date: String = "",
    //val rate: Map<String, Double> = mapOf(Pair("", 0.0)),
)