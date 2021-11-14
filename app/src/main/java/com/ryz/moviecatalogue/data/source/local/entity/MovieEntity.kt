package com.ryz.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "poster")
    val poster: String? = null,

    @ColumnInfo(name = "years")
    val years: String? = null,

    @ColumnInfo(name = "score")
    val score: Double? = 0.0,

    @ColumnInfo(name = "duration")
    val duration: Int? = 0,

    @ColumnInfo(name = "genre")
    val genre: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "isFav")
    var isFav: Boolean = false
)
