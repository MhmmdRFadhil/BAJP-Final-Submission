package com.ryz.moviecatalogue.ui.activity

import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.model.CatalogueEntity
import com.ryz.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var catalogue: CatalogueEntity

    fun setCatalogue(id: Int?, category: String) {
        when (category) {
            MOVIE -> {
                DataDummy.getMovie().forEach { movie ->
                    if (movie.id == id) catalogue = movie
                }
            }
            TV -> {
                DataDummy.getTvShow().forEach { tvShow ->
                    if (tvShow.id == id) catalogue = tvShow
                }
            }
        }
    }

    fun getCatalogue() = catalogue

    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
    }
}