package com.devkproject.movieinfo2.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}