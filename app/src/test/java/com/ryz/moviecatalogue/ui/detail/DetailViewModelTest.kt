package com.ryz.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.ui.detail.DetailViewModel.Companion.TYPE_MOVIE
import com.ryz.moviecatalogue.ui.detail.DetailViewModel.Companion.TYPE_TV_SHOW
import com.ryz.moviecatalogue.utils.DataDummy
import com.ryz.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovieDetail = Resource.success(DataDummy.getMovieDetail())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieDetail

        dummyMoviesId?.let {
            `when`(catalogueRepository.getDetailNowPlayingMovies(it)).thenReturn(movie)
            viewModel.setCatalogue(it, TYPE_MOVIE)
            val detailEntity = viewModel.getDetailMovie().value?.data
            verify(catalogueRepository).getDetailNowPlayingMovies(it)

            assertNotNull(detailEntity)
            assertEquals(dummyMovies.id, detailEntity?.id)
            assertEquals(dummyMovies.title, detailEntity?.title)
            assertEquals(dummyMovies.genre, detailEntity?.genre)
            assertEquals(dummyMovies.score, detailEntity?.score)
            assertEquals(dummyMovies.duration, detailEntity?.duration)
            assertEquals(dummyMovies.years, detailEntity?.years)
            assertEquals(dummyMovies.overview, detailEntity?.overview)
            assertEquals(dummyMovies.poster, detailEntity?.poster)

            viewModel.getDetailMovie().observeForever(movieObserver)
            verify(movieObserver).onChanged(dummyMovieDetail)
        }
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovieDetail = Resource.success(DataDummy.getMovieDetail())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieDetail

        dummyMoviesId?.let {
            `when`(catalogueRepository.getDetailNowPlayingMovies(it)).thenReturn(movie)
            viewModel.setCatalogue(it, TYPE_MOVIE)
            val detailEntity = viewModel.getDetailMovie().value?.data
            verify(catalogueRepository).getDetailNowPlayingMovies(it)

            assertNotNull(detailEntity)
            assertEquals(dummyMovies.id, detailEntity?.id)
            assertEquals(dummyMovies.title, detailEntity?.title)
            assertEquals(dummyMovies.genre, detailEntity?.genre)
            assertEquals(dummyMovies.score, detailEntity?.score)
            assertEquals(dummyMovies.duration, detailEntity?.duration)
            assertEquals(dummyMovies.years, detailEntity?.years)
            assertEquals(dummyMovies.overview, detailEntity?.overview)
            assertEquals(dummyMovies.poster, detailEntity?.poster)

            viewModel.setFavoriteMovie(dummyMovies)
            verify(catalogueRepository).setFavoriteMovie(movie.value?.data as MovieEntity, true)
            verifyNoMoreInteractions(movieObserver)
        }
    }

    @Before
    fun setUpTvShow() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShowDetail = Resource.success(DataDummy.getTvShowDetail())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShowDetail

        dummyTvShowId?.let {
            `when`(catalogueRepository.getDetailTvAiringToday(it)).thenReturn(tvShow)
            viewModel.setCatalogue(it, TYPE_TV_SHOW)
            val detailEntity = viewModel.getDetailTvShow().value?.data
            verify(catalogueRepository).getDetailTvAiringToday(it)

            assertNotNull(detailEntity)
            assertEquals(dummyTvShow.id, detailEntity?.id)
            assertEquals(dummyTvShow.title, detailEntity?.title)
            assertEquals(dummyTvShow.genre, detailEntity?.genre)
            assertEquals(dummyTvShow.score, detailEntity?.score)
            assertEquals(dummyTvShow.duration, detailEntity?.duration)
            assertEquals(dummyTvShow.years, detailEntity?.years)
            assertEquals(dummyTvShow.overview, detailEntity?.overview)
            assertEquals(dummyTvShow.poster, detailEntity?.poster)

            viewModel.getDetailTvShow().observeForever(tvShowObserver)
            verify(tvShowObserver).onChanged(dummyTvShowDetail)
        }
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyTvShowDetail = Resource.success(DataDummy.getTvShowDetail())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShowDetail

        dummyMoviesId?.let {
            `when`(catalogueRepository.getDetailTvAiringToday(it)).thenReturn(tvShow)
            viewModel.setCatalogue(it, TYPE_TV_SHOW)
            val detailEntity = viewModel.getDetailTvShow().value?.data
            verify(catalogueRepository).getDetailTvAiringToday(it)

            assertNotNull(detailEntity)
            assertEquals(dummyTvShow.id, detailEntity?.id)
            assertEquals(dummyTvShow.title, detailEntity?.title)
            assertEquals(dummyTvShow.genre, detailEntity?.genre)
            assertEquals(dummyTvShow.score, detailEntity?.score)
            assertEquals(dummyTvShow.duration, detailEntity?.duration)
            assertEquals(dummyTvShow.years, detailEntity?.years)
            assertEquals(dummyTvShow.overview, detailEntity?.overview)
            assertEquals(dummyTvShow.poster, detailEntity?.poster)

            viewModel.setFavoriteTvShow(dummyTvShow)
            verify(catalogueRepository).setFavoriteTvShow(tvShow.value?.data as TvShowEntity, true)
            verifyNoMoreInteractions(tvShowObserver)
        }
    }
}