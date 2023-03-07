package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {
    @TypeConverter
    fun toString(collection: List<Int>?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): List<Int>? {
        val listVOType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}