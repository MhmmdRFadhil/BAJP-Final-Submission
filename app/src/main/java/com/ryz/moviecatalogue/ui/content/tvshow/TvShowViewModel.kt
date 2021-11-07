package com.ryz.moviecatalogue.ui.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShowAiringToday(): LiveData<List<CatalogueEntity>> =
        catalogueRepository.getTvAiringToday()
}