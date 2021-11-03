package com.ryz.moviecatalogue.interfaces

import com.ryz.moviecatalogue.model.CatalogueEnitity

interface ItemClickedCallback {
    fun onItemClick(catalogueEnitity: CatalogueEnitity)
}