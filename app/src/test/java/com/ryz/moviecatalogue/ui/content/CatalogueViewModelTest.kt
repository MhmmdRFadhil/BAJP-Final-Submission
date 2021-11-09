package com.ryz.moviecatalogue.ui.content

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueViewModelTest {

    private lateinit var catalogueViewModel: CatalogueViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<CatalogueEntity>>

    @Before
    fun setUp() {
        catalogueViewModel = CatalogueViewModel(catalogueRepository)
    }

    @Test
    fun getMovieNowPlaying() {
        val dummyMovies = DataDummy.getMovie()
        val movies = MutableLiveData<List<CatalogueEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(catalogueRepository.getNowPlayingMovies()).thenReturn(movies)
        val movie = catalogueViewModel.getMovieNowPlaying().value
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        catalogueViewModel.getMovieNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getTvShowAiringToday() {
        val dummyTvShow = DataDummy.getTvShow()
        val tvShows = MutableLiveData<List<CatalogueEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvAiringToday()).thenReturn(tvShows)
        val tvShow = catalogueViewModel.getTvShowAiringToday().value
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)
        catalogueViewModel.getTvShowAiringToday().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}