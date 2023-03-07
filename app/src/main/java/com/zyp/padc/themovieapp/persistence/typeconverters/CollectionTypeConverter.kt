package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zyp.padc.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): CollectionVO? {
        val listVOType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}