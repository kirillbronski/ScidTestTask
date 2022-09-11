package com.bronski.android.scidtesttask.core.data.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val resultData: List<ResultData>,
    @SerializedName("next_page_url") val nextPageUrl: String,
    @SerializedName("prev_page_url") val prevPageUrl: String,
)