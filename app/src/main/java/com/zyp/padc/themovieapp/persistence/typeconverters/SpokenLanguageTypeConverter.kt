package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zyp.padc.themovieapp.data.vos.CollectionVO
import com.zyp.padc.themovieapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(collection: List<SpokenLanguageVO>?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): List<SpokenLanguageVO>? {
        val listVOType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}