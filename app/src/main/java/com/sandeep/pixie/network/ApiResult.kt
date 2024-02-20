package com.sandeep.pixie.network

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
sealed class ApiResult<T> {

    data class Loading<T>(val isLoading: Boolean) : ApiResult<T>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failure<T>(val errorMessage: String) : ApiResult<T>()
}