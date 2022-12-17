package com.devkproject.movieinfo2.data.model.video

data class Videos(
    val id: Int,
    val results: ArrayList<VideoItems>
)

data class VideoItems(
    val id: String,
    val key: String,
    val name: String,
    val type: String
)