package com.ryz.moviecatalogue.viewmodel

import com.ryz.moviecatalogue.ui.fragment.tvshow.TvShowViewModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }
}