package com.ryz.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,
)

