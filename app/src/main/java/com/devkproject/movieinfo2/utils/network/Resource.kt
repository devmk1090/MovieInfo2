package com.devkproject.movieinfo2.utils.network

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? = null,
    val httpStatusCode: Int? = null) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }
        fun <T> error(message: String, data: T?, httpStatusCode: Int? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                message,
                httpStatusCode
            )
        }
    }
    var isSuccessful = status == Status.SUCCESS
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class HttpErrorResponse(
    var message:String
)