package com.ryz.moviecatalogue.ui.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.ryz.moviecatalogue.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getTvShow() = DataDummy.getTvShow()
}