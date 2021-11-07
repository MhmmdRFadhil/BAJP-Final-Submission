package com.ryz.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class GenreMoviesDetail(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
