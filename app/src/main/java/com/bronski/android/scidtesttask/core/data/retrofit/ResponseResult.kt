package com.bronski.android.scidtesttask.core.data.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("data") val resultData: List<ResultData>,
)