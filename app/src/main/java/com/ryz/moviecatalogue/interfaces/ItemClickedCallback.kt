package com.ryz.moviecatalogue.interfaces

import com.ryz.moviecatalogue.model.CatalogueEntity

interface ItemClickedCallback {
    fun onItemClick(catalogueEntity: CatalogueEntity)
}