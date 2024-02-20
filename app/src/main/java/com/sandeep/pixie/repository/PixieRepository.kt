package com.sandeep.pixie.repository

import com.sandeep.pixie.network.ApiResult
import com.sandeep.pixie.network.ApiService
import com.sandeep.pixie.utils.EMPTY_LIST
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 * Red Apple Technologies Private Limited.
 */
class PixieRepository @Inject constructor(private val apiService: ApiService) {

    // Repository to get the required data from the API
    suspend fun getList(page: Int, limit: Int) = flow {
        emit(ApiResult.Loading(true))
        val response = apiService.getList(page, limit)
        if (response.isEmpty()) {
            emit(ApiResult.Failure(EMPTY_LIST))
        } else {
            emit(ApiResult.Success(response))
        }

    }.catch { error ->
        emit(ApiResult.Failure( error.message ?: "Unknown error"))
    }
}