package com.zyp.padc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zyp.padc.themovieapp.data.vos.ProductionCompanyVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(collection: List<ProductionCompanyVO>?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toListVO(jsonString: String): List<ProductionCompanyVO>? {
        val listVOType = object : TypeToken<List<ProductionCompanyVO>?>() {}.type
        return Gson().fromJson(jsonString, listVOType)
    }
}