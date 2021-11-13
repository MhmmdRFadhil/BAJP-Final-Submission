package com.ryz.moviecatalogue.di

import android.content.Context
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.LocalDataSource
import com.ryz.moviecatalogue.data.source.local.room.CatalogueDatabase
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource
import com.ryz.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideCatalogueRepository(context: Context): CatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()
        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}