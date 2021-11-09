package com.ryz.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @field:SerializedName("results")
    val result: List<T>? = null
)
