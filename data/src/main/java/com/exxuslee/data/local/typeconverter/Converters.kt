package com.exxuslee.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by Mayokun Adeniyi on 23/07/2020.
 */

class Converters {
    val gson = Gson()
    val typeRates: Type = object : TypeToken<Map<String, Double>?>() {}.type


    @TypeConverter
    fun fromRates(rates: Map<String, Double>?): String{
        return gson.toJson(rates,typeRates)
    }

    @TypeConverter
    fun toRates(json: String?): Map<String, Double> {
        return gson.fromJson(json,typeRates)
    }
}