package com.ryz.moviecatalogue.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogueEntity(
    val id: Int? = 0,
    val title: String? = null,
    val years: String? = null,
    val score: String? = null,
    val duration: String? = null,
    val genre: String? = null,
    val overview: String? = null,
    val poster: Int? = 0
): Parcelable