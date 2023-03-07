package com.zyp.padc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class CollectionVO(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)