package com.sandeep.pixie.network

import com.sandeep.pixie.model.PixieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 * Red Apple Technologies Private Limited.
 */
interface ApiService {

    @GET("v2/list")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ) : PixieResponse
}