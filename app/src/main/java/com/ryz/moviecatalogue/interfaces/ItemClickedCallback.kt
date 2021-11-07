package com.ryz.moviecatalogue.interfaces

import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

interface ItemClickedCallback {
    fun onItemClick(catalogueEntity: CatalogueEntity)
}