package com.ryz.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavMovie() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(catalogueRepository.getFavoriteMovie()).thenReturn(movies)
        val movie = viewModel.getFavMovie().value
        verify(catalogueRepository).getFavoriteMovie()
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        viewModel.getFavMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getFavTvShow() {
        val dummyTvShow = tvShowPagedList
        `when`(dummyTvShow.size).thenReturn(10)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(catalogueRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShow().value
        verify(catalogueRepository).getFavoriteTvShow()
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)

        viewModel.getFavTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)

    }
}