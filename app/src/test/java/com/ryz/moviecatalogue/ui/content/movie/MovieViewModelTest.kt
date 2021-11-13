package com.ryz.moviecatalogue.ui.content.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.vo.Resource
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
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovieNowPlaying() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getNowPlayingMovies("title_descending")).thenReturn(movies)
        val movie = movieViewModel.getMovieNowPlaying("title_descending").value?.data
        verify(catalogueRepository).getNowPlayingMovies("title_descending")
        assertNotNull(movie)
        assertEquals(10, movie?.size)

        movieViewModel.getMovieNowPlaying("title_descending").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}