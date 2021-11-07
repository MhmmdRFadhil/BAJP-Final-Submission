package com.ryz.moviecatalogue.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.DetailEntity

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    private lateinit var detail: LiveData<DetailEntity>

    fun setCatalogue(id: Int, category: String) {
        when (category) {
            TYPE_MOVIE -> {
                detail = catalogueRepository.getDetailNowPlayingMovies(id)
            }
            TYPE_TV_SHOW -> {
                detail = catalogueRepository.getDetailTvAiringToday(id)
            }
        }
    }

    fun getCatalogue() = detail

    companion object {
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV_SHOW = "tv"
    }
}