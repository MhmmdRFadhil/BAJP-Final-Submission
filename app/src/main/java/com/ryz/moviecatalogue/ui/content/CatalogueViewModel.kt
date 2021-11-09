package com.ryz.moviecatalogue.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

class CatalogueViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovieNowPlaying(): LiveData<List<CatalogueEntity>> =
        catalogueRepository.getNowPlayingMovies()

    fun getTvShowAiringToday(): LiveData<List<CatalogueEntity>> =
        catalogueRepository.getTvAiringToday()
}