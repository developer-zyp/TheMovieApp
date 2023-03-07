package com.zyp.padc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.zyp.padc.themovieapp.data.vos.GenreVO

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>?
)