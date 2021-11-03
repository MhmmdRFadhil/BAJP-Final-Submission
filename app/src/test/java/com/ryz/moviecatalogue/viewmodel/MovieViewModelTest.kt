package com.ryz.moviecatalogue.viewmodel

import com.ryz.moviecatalogue.ui.fragment.movie.MovieViewModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(10, movie.size)
    }
}