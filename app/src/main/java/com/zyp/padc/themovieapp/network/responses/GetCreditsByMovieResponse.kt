package com.zyp.padc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.zyp.padc.themovieapp.data.vos.ActorVO

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?
)
