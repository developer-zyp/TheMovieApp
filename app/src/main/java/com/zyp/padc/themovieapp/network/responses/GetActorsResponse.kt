package com.zyp.padc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.zyp.padc.themovieapp.data.vos.ActorVO

data class GetActorsResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ActorVO>?
)
