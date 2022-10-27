package com.devkproject.movieinfo2.data.model.artist

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("id")
    val id: Int,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("job")
    val job: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
)
