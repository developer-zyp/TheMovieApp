package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zyp.padc.themovieapp.data.vos.CollectionVO
import com.zyp.padc.themovieapp.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(collection: List<GenreVO>?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): List<GenreVO>? {
        val listVOType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}