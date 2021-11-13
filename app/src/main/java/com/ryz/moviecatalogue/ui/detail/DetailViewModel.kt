package com.ryz.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.vo.Resource

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    private lateinit var detailMovies: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun setCatalogue(id: Int, category: String) {
        when (category) {
            TYPE_MOVIE -> {
                detailMovies = catalogueRepository.getDetailNowPlayingMovies(id)
            }
            TYPE_TV_SHOW -> {
                detailTvShow = catalogueRepository.getDetailTvAiringToday(id)
            }
        }
    }

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav
        catalogueRepository.setFavoriteMovie(movieEntity, newState)

    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFav
        catalogueRepository.setFavoriteTvShow(tvShowEntity, newState)
    }

    fun getDetailMovie() = detailMovies
    fun getDetailTvShow() = detailTvShow

    companion object {
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV_SHOW = "tv"
    }
}