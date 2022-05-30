package com.exxuslee.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxuslee.data.remote.response.Bank
import com.exxuslee.data.remote.response.Country
import com.exxuslee.data.utils.Constants.CARD_INFO_TABLE_NAME

/**
 * Created by Exxus Lee on 22/07/2020.
 */

@Entity(tableName = CARD_INFO_TABLE_NAME)
data class PriceEntity(

    @PrimaryKey
    @ColumnInfo(name = "unique_id")
    val id: Int? = 0,
    val bank: Bank?,
    val brand: String? = "",
    val country: Country?,
    val type: String? = ""
)
