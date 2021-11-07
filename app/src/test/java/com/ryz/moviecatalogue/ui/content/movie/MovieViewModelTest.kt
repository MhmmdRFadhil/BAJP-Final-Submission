package com.ryz.moviecatalogue.ui.content.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<CatalogueEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DataDummy.getMovie()
        val movies = MutableLiveData<List<CatalogueEntity>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getNowPlayingMovies()).thenReturn(movies)
        val movie = viewModel.getMovieNowPlaying().value
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        viewModel.getMovieNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}