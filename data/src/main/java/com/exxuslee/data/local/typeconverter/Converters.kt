package com.exxuslee.data.local.typeconverter

import androidx.collection.ArrayMap
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()
    private val typeRates: Type = object : TypeToken<ArrayMap<String, Double>?>() {}.type


    @TypeConverter
    fun fromRates(rates: ArrayMap<String, Double>?): String{
        return gson.toJson(rates,typeRates)
    }

    @TypeConverter
    fun toRates(json: String?): ArrayMap<String, Double> {
        return gson.fromJson(json,typeRates)
    }
}