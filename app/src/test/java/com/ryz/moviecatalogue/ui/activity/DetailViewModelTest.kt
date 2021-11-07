package com.ryz.moviecatalogue.ui.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.entity.DetailEntity
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovies = DataDummy.getMovieDetail()
    private val dummyTvShow = DataDummy.getTvShowDetail()

    private val dummyMoviesId = dummyMovies.id
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var catalogueObserver: Observer<DetailEntity>

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovies() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyMovies

        dummyMoviesId?.let {
            `when`(catalogueRepository.getDetailNowPlayingMovies(it)).thenReturn(movie)
            viewModel.setCatalogue(it, DetailViewModel.TYPE_MOVIE)
            val detailEntity = viewModel.getCatalogue().value as DetailEntity
            verify(catalogueRepository).getDetailNowPlayingMovies(it)

            assertNotNull(detailEntity)
            assertEquals(dummyMovies.id, detailEntity.id)
            assertEquals(dummyMovies.title, detailEntity.title)
            assertEquals(dummyMovies.genre, detailEntity.genre)
            assertEquals(dummyMovies.score, detailEntity.score)
            assertEquals(dummyMovies.duration, detailEntity.duration)
            assertEquals(dummyMovies.years, detailEntity.years)
            assertEquals(dummyMovies.overview, detailEntity.overview)
            assertEquals(dummyMovies.poster, detailEntity.poster)

            viewModel.getCatalogue().observeForever(catalogueObserver)
            verify(catalogueObserver).onChanged(dummyMovies)
        }
    }

    @Before
    fun setUpTvShow() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyTvShow

        dummyTvShowId?.let {
            `when`(catalogueRepository.getDetailTvAiringToday(it)).thenReturn(tvShow)
            viewModel.setCatalogue(it, DetailViewModel.TYPE_TV_SHOW)
            val detailEntity = viewModel.getCatalogue().value as DetailEntity

            assertNotNull(detailEntity)
            assertEquals(dummyTvShow.id, detailEntity.id)
            assertEquals(dummyTvShow.title, detailEntity.title)
            assertEquals(dummyTvShow.genre, detailEntity.genre)
            assertEquals(dummyTvShow.score, detailEntity.score)
            assertEquals(dummyTvShow.duration, detailEntity.duration)
            assertEquals(dummyTvShow.years, detailEntity.years)
            assertEquals(dummyTvShow.overview, detailEntity.overview)
            assertEquals(dummyTvShow.poster, detailEntity.poster)

            viewModel.getCatalogue().observeForever(catalogueObserver)
            verify(catalogueObserver).onChanged(dummyTvShow)
        }
    }
}