package com.ryz.moviecatalogue.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class GenreTvShowDetail(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
