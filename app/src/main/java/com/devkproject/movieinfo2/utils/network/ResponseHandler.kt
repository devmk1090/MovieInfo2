package com.devkproject.movieinfo2.utils.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

private fun <T : Any> createSuccessResource(data: T): Resource<T> {
    return Resource.success(data)
}

private fun <T : Any> createErrorResource(e: Exception): Resource<T> {
    return when (e) {
        is HttpException -> {
            try {
                val errorJson = e.response()?.errorBody()?.string()?.fromJson<HttpErrorResponse>()
                Resource.error(
                    getErrorMessage(
                        e.code(),
                        errorJson?.message
                    ), null, e.code()
                )
            } catch (ex: Exception) {
                Resource.error(getErrorMessage(e.code()), null, e.code())
            }
        }
        is SocketTimeoutException -> Resource.error(
            getErrorMessage(
                ErrorCodes.SocketTimeOut.code,
                "요청시간 초과.\n인터넷 환경을 확인해주세요."
            ), null, ErrorCodes.SocketTimeOut.code
        )
        else -> {
            Resource.error(
                getErrorMessage(
                    Int.MAX_VALUE
                ), null
            )
        }
    }
}

suspend fun <T : Any> handleApiResponse(f: suspend () -> T): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: T = f()
            createSuccessResource(response)
        } catch (e: Exception) {
            createErrorResource(e)
        }
    }
}

private fun getErrorMessage(httpStatusCode: Int, errorMessage: String? = null): String {
    return when (httpStatusCode) {
        ErrorCodes.SocketTimeOut.code -> errorMessage!!
        401 -> if (errorMessage.isNullOrBlank()) "401 error" else errorMessage
        in 400..405 -> if (errorMessage.isNullOrBlank()) "400 ~ 405 error" else errorMessage
        500 -> if (errorMessage.isNullOrBlank()) "500 error" else errorMessage
        else -> if (errorMessage.isNullOrBlank()) "알 수 없는 오류가 발생하였습니다." else errorMessage
    }
}