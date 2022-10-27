package com.devkproject.movieinfo2.data.model.artist

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
)
