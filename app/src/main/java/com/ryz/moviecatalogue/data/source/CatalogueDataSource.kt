package com.ryz.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getNowPlayingMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailNowPlayingMovies(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movieEntity: MovieEntity, state: Boolean)

    fun getTvAiringToday(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvAiringToday(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state: Boolean)
}