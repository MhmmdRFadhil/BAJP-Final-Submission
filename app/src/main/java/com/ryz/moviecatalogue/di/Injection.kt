package com.ryz.moviecatalogue.di

import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideCatalogueRepository(): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}