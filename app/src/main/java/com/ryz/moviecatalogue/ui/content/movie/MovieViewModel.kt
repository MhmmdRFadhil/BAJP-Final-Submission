package com.ryz.moviecatalogue.ui.content.movie

import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovieNowPlaying(sort: String) = catalogueRepository.getNowPlayingMovies(sort)
}