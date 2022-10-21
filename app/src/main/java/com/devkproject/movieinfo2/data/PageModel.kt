package com.devkproject.movieinfo2.data

import com.google.gson.annotations.SerializedName

data class PageModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val results: List<MovieItem>,

)
