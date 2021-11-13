package com.ryz.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "years")
    var years: String? = null,

    @ColumnInfo(name = "score")
    var score: Double? = 0.0,

    @ColumnInfo(name = "duration")
    var duration: Int? = 0,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "isFav")
    var isFav: Boolean = false
)
