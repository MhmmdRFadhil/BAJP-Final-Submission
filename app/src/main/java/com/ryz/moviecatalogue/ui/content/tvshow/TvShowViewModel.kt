package com.ryz.moviecatalogue.ui.content.tvshow

import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShowAiringToday(sort: String) = catalogueRepository.getTvAiringToday(sort)
}