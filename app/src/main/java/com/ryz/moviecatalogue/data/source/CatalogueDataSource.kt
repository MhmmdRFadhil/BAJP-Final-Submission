package com.ryz.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.ryz.moviecatalogue.data.source.entity.DetailEntity
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

interface CatalogueDataSource {
    fun getNowPlayingMovies(): LiveData<List<CatalogueEntity>>

    fun getDetailNowPlayingMovies(movieId: Int): LiveData<DetailEntity>

    fun getTvAiringToday(): LiveData<List<CatalogueEntity>>

    fun getDetailTvAiringToday(tvShowId: Int): LiveData<DetailEntity>
}