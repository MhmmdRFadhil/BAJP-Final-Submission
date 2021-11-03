package com.ryz.moviecatalogue.viewmodel

import com.ryz.moviecatalogue.ui.activity.DetailViewModel
import com.ryz.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovies = DataDummy.getMovie()[0]
    private val dummyTvShow = DataDummy.getTvShow()[0]

    private val dummyMoviesId = dummyMovies.id
    private val dummyTvShowId = dummyTvShow.id

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel()
        viewModel.setCatalogue(dummyMoviesId, "movie")
    }

    @Test
    fun getMovies() {
        viewModel.setCatalogue(dummyMovies.id, "movie")
        val movies = viewModel.getCatalogue()
        assertNotNull(movies)
        assertEquals(dummyMovies.id, movies.id)
        assertEquals(dummyMovies.title, movies.title)
        assertEquals(dummyMovies.genre, movies.genre)
        assertEquals(dummyMovies.score, movies.score)
        assertEquals(dummyMovies.duration, movies.duration)
        assertEquals(dummyMovies.years, movies.years)
        assertEquals(dummyMovies.overview, movies.overview)
        assertEquals(dummyMovies.poster, movies.poster)
    }

    @Before
    fun setUpTvShow() {
        viewModel = DetailViewModel()
        viewModel.setCatalogue(dummyTvShowId, "tv")
    }

    @Test
    fun getTvShow() {
        viewModel.setCatalogue(dummyTvShow.id, "tv")
        val tvShow = viewModel.getCatalogue()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.score, tvShow.score)
        assertEquals(dummyTvShow.duration, tvShow.duration)
        assertEquals(dummyTvShow.years, tvShow.years)
        assertEquals(dummyTvShow.overview, tvShow.overview)
        assertEquals(dummyTvShow.poster, tvShow.poster)
    }
}