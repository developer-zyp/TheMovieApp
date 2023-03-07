package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zyp.padc.themovieapp.data.vos.ProductionCountryVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(collection: List<ProductionCountryVO>?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): List<ProductionCountryVO>? {
        val listVOType = object : TypeToken<List<ProductionCountryVO>?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}