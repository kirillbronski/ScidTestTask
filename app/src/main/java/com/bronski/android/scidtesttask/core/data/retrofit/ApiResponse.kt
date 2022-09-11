package com.bronski.android.scidtesttask.core.data.retrofit

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("result") val responseResult: ResponseResult
)