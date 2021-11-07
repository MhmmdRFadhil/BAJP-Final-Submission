package com.ryz.moviecatalogue.ui.content.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovieNowPlaying(): LiveData<List<CatalogueEntity>> =
        catalogueRepository.getNowPlayingMovies()
}