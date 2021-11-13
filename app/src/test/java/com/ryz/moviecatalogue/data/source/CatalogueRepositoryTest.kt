package com.ryz.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.ryz.moviecatalogue.data.source.local.LocalDataSource
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource
import com.ryz.moviecatalogue.utils.AppExecutors
import com.ryz.moviecatalogue.utils.DataDummy
import com.ryz.moviecatalogue.utils.LiveDataTestUtil
import com.ryz.moviecatalogue.utils.PagedListUtils
import com.ryz.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule

import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.getMovieResponse()
    private val tvShowResponse = DataDummy.getTvShowResponse()

    private val movieId = movieResponse[0].id
    private val tvShowId = tvShowResponse[0].id

    private val movieDetail = DataDummy.getRemoteDetailMovies()
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getNowPlayingMovies() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovie("title_descending")).thenReturn(dataSource)
        catalogueRepository.getNowPlayingMovies("title_descending")

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.getMovie()))
        verify(local).getAllMovie("title_descending")
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailNowPlayingMovies() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getMovieDetail()
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getDetailNowPlayingMovies(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSource)
        catalogueRepository.getFavoriteMovie()

        val favoriteMovieEntities =
            Resource.success(PagedListUtils.mockPagedList(DataDummy.getMovie()))
        verify(local).getFavoriteMovie()
        assertNotNull(favoriteMovieEntities)
        assertEquals(movieResponse.size, favoriteMovieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        catalogueRepository.setFavoriteMovie(DataDummy.getMovieDetail(), true)
        verify(local).setFavoriteMovies(DataDummy.getMovieDetail(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvAiringToday() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow("title_descending")).thenReturn(dataSource)
        catalogueRepository.getTvAiringToday("title_descending")

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.getTvShow()))
        verify(local).getAllTvShow("title_descending")
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvAiringToday() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getTvShowDetail()
        `when`(local.getTvShowById(movieId)).thenReturn(dummyDetail)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getDetailTvAiringToday(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSource)
        catalogueRepository.getFavoriteTvShow()

        val favoriteTvShowEntities =
            Resource.success(PagedListUtils.mockPagedList(DataDummy.getTvShow()))
        verify(local).getFavoriteTvShow()
        assertNotNull(favoriteTvShowEntities)
        assertEquals(tvShowResponse.size, favoriteTvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        catalogueRepository.setFavoriteTvShow(DataDummy.getTvShowDetail(), true)
        verify(local).setFavoriteTvShow(DataDummy.getTvShowDetail(), true)
        verifyNoMoreInteractions(local)
    }
}