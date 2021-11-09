package com.ryz.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.di.Injection
import com.ryz.moviecatalogue.ui.activity.DetailViewModel
import com.ryz.moviecatalogue.ui.content.CatalogueViewModel

class ViewModelFactory private constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CatalogueViewModel::class.java) -> {
                CatalogueViewModel(catalogueRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(catalogueRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class : ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideCatalogueRepository())
            }
    }
}