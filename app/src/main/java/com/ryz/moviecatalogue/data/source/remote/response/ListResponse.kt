package com.ryz.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @field:SerializedName("status_message")
    val statusMessage: String? = null,

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("results")
    val result: List<T>? = null
)
