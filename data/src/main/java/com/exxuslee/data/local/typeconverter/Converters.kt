package com.exxuslee.data.local.typeconverter

import androidx.collection.ArrayMap
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()
    private val typeRates: Type = object : TypeToken<ArrayMap<String, Double>?>() {}.type
    private val typeCurrency: Type = object : TypeToken<ArrayMap<String, String>?>() {}.type
    private val typeFavorite: Type = object : TypeToken<ArrayList<Boolean>?>() {}.type

    @TypeConverter
    fun fromRates(rates: ArrayMap<String, Double>?): String {
        return gson.toJson(rates, typeRates)
    }

    @TypeConverter
    fun toRates(json: String?): ArrayMap<String, Double> {
        return gson.fromJson(json, typeRates)
    }

    @TypeConverter
    fun fromCurrency(currency: ArrayMap<String, String>?): String {
        return gson.toJson(currency, typeCurrency)
    }

    @TypeConverter
    fun toCurrency(json: String?): ArrayMap<String, String> {
        return gson.fromJson(json, typeCurrency)
    }

    @TypeConverter
    fun fromFavorite(currency: ArrayList<Boolean>?): String {
        return gson.toJson(currency, typeFavorite)
    }

    @TypeConverter
    fun toFavorite(json: String?): ArrayList<Boolean> {
        return gson.fromJson(json, typeFavorite)
    }
}