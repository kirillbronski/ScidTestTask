package com.bronski.android.scidtesttask.core.data.retrofit

import com.bronski.android.scidtesttask.core.utils.Constants.API_BOOKS
import retrofit2.http.GET
import retrofit2.http.Query

interface ScidApi {

    @GET(API_BOOKS)
    suspend fun getUsersResult(
        @Query("page") page: Int
    ): ApiResponse

}