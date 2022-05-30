package com.exxuslee.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.exxuslee.data.remote.response.base
import com.exxuslee.data.remote.response.Country
import java.lang.reflect.Type

/**
 * Created by Exxus Lee on 23/07/2020.
 */

class Converters {
    val gson = Gson()

    val typebase: Type = object : TypeToken<base?>() {}.type
    val typeCountry: Type = object : TypeToken<Country?>() {}.type

    @TypeConverter
    fun frombase(base: base?): String{
        return gson.toJson(base,typebase)
    }

    @TypeConverter
    fun tobase(json: String?): base {
        return gson.fromJson(json,typebase)
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