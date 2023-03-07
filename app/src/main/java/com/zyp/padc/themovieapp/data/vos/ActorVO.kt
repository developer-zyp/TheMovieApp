package com.zyp.padc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("known_for")
    val knownFor: List<MovieVO>?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("cast_id")
    val castId: Int?,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("job")
    val job: String?,
    @SerializedName("order")
    val order: Int?,
)