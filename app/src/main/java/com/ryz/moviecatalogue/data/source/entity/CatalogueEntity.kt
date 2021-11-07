package com.ryz.moviecatalogue.data.source.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogueEntity(
    val id: Int? = 0,
    val title: String? = null,
    val poster: String? = null
) : Parcelable
