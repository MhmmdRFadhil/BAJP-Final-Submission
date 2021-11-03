package com.ryz.moviecatalogue.ui.fragment.movie

import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.utils.DataDummy

class MovieViewModel: ViewModel() {
    fun getMovie() = DataDummy.getMovie()
}