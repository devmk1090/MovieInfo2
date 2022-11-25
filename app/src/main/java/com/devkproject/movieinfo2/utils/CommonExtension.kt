package com.devkproject.movieinfo2.utils

import kotlin.time.Duration.Companion.minutes

fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}시간 ${this % 60}분"
}

fun Int.genderInString(): String {
    return when (this) {
        1 -> "Female"
        2 -> "Male"
        else -> ""
    }
}