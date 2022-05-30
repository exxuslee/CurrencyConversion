package com.exxuslee.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxuslee.data.utils.Constants.CARD_INFO_TABLE_NAME

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Entity(tableName = CARD_INFO_TABLE_NAME)
data class PriceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "base") val base: String? = "",
    @ColumnInfo(name = "date") val date: String? = "",
    @ColumnInfo(name = "rate") val rate: Map<String, Double> = mapOf(Pair("", 0.0)),
)
