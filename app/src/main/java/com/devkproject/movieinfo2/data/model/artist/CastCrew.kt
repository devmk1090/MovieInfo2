package com.devkproject.movieinfo2.data.model.artist

import com.google.gson.annotations.SerializedName

data class CastCrew(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
)
