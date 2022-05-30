package com.exxuslee.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.exxuslee.data.remote.response.Bank
import com.exxuslee.data.remote.response.Country
import java.lang.reflect.Type

/**
 * Created by Exxus Lee on 23/07/2020.
 */

class Converters {
    val gson = Gson()

    val typeBank: Type = object : TypeToken<Bank?>() {}.type
    val typeCountry: Type = object : TypeToken<Country?>() {}.type

    @TypeConverter
    fun fromBank(bank: Bank?): String{
        return gson.toJson(bank,typeBank)
    }

    @TypeConverter
    fun toBank(json: String?): Bank {
        return gson.fromJson(json,typeBank)
    }

    @TypeConverter
    fun fromCountry(country: Country?): String{
        return gson.toJson(country,typeCountry)
    }

    @TypeConverter
    fun toCountry(json: String?): Country {
        return gson.fromJson(json,typeCountry)
    }
}