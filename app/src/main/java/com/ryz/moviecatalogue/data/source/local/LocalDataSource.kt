package com.ryz.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.data.source.local.room.CatalogueDao
import com.ryz.moviecatalogue.utils.SortUtils
import com.ryz.moviecatalogue.utils.SortUtils.MOVIE_ENTITIES
import com.ryz.moviecatalogue.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    fun getAllMovie(sort: String): DataSource.Factory<Int, MovieEntity> =
        catalogueDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getMovieById(id: Int): LiveData<MovieEntity> = catalogueDao.getMoviesById(id)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
        catalogueDao.getFavMovies()

    fun getAllTvShow(sort: String): DataSource.Factory<Int, TvShowEntity> =
        catalogueDao.getTvShow(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = catalogueDao.getTvShowById(id)

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> =
        catalogueDao.getFavTvShow()

    fun insertMovies(movieEntity: List<MovieEntity>) =
        catalogueDao.insertMovies(movieEntity)

    fun setFavoriteMovies(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFav = newState
        catalogueDao.updateMovies(movieEntity)
    }

    fun updateMovies(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFav = newState
        catalogueDao.updateMovies(movieEntity)
    }

    fun insertTvShow(tvShowEntity: List<TvShowEntity>) =
        catalogueDao.insertTvShow(tvShowEntity)

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, newState: Boolean) {
        tvShowEntity.isFav = newState
        catalogueDao.updateTvShow(tvShowEntity)
    }

    fun updateTvShow(tvShowEntity: TvShowEntity, newState: Boolean) {
        tvShowEntity.isFav = newState
        catalogueDao.updateTvShow(tvShowEntity)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }
}