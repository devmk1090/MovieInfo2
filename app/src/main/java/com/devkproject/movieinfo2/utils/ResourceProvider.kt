package com.devkproject.movieinfo2.utils

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)

    fun getStringArray(@ArrayRes resId: Int): Array<String> =
        context.resources.getStringArray(resId)

    @ColorInt
    fun getColor(@ColorRes colorId: Int): Int =
        ContextCompat.getColor(context, colorId)

}