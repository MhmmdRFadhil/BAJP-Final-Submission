package com.ryz.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavMovie(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovie()
    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavoriteTvShow()
}