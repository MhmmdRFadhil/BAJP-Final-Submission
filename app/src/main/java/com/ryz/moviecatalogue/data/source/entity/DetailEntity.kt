package com.ryz.moviecatalogue.data.source.entity

data class DetailEntity(
    val id: Int? = 0,
    val title: String? = null,
    val years: String? = null,
    val score: Double? = 0.0,
    val duration: Int? = 0,
    val genre: List<String>? = null,
    val overview: String? = null,
    val poster: String? = null
)