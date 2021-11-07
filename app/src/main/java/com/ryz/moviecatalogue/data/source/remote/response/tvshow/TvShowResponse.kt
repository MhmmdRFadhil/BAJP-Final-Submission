package com.ryz.moviecatalogue.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("poster_path")
    val posterPath: String,
)
