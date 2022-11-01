package com.devkproject.movieinfo2.utils.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import kotlin.jvm.Throws

enum class GsonHolder constructor(val gson: Gson) {

    GSON(GsonBuilder().serializeNulls().setPrettyPrinting().create());

    @Throws(JsonSyntaxException::class)
    fun <T> fromJson(json: String, classOfT: Class<T>): T = gson.fromJson(json, classOfT)

    @Throws(JsonSyntaxException::class)
    fun <T> fromJson(json: JsonElement, classOfT: Class<T>): T = gson.fromJson(json, classOfT)

}

inline fun <reified T : Any> String.fromJson(): T = GsonHolder.GSON.fromJson(this, T::class.java)

inline fun <reified T : Any> JsonElement.fromJson(): T = GsonHolder.GSON.fromJson(this, T::class.java)