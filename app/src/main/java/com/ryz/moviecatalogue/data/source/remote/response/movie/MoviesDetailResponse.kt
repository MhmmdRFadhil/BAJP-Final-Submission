package com.ryz.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesDetailResponse(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genres")
    val genres: List<GenreMoviesDetail>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)
