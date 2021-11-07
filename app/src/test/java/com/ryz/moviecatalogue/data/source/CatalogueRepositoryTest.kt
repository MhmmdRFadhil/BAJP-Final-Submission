package com.ryz.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource
import com.ryz.moviecatalogue.utils.DataDummy
import com.ryz.moviecatalogue.utils.LiveDataTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule

import org.junit.Test
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val movieResponse = DataDummy.getMovieResponse()
    private val tvShowResponse = DataDummy.getTvShowResponse()

    private val movieId = movieResponse[0].id
    private val tvShowId = tvShowResponse[0].id

    private val movieDetail = DataDummy.getRemoteDetailMovies()
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getNowPlayingMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.LoadNowPlayingMoviesCallback).onAllNowPlayingMovieReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getNowPlayingMovies(any())
        }

        val data = LiveDataTestUtil.getValue(catalogueRepository.getNowPlayingMovies())

        runBlocking {
            verify(remote).getNowPlayingMovies(any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.size.toLong(), data.size.toLong())

    }

    @Test
    fun getDetailNowPlayingMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as RemoteDataSource.LoadDetailNowPlayingMoviesCallback).onDetailNowPlayingMovieReceived(
                    movieDetail
                )
                null
            }.`when`(remote).getDetailNowPlayingMovies(eq(movieId), any())
        }
        val data = LiveDataTestUtil.getValue(catalogueRepository.getDetailNowPlayingMovies(movieId))

        runBlocking {
            verify(remote).getDetailNowPlayingMovies(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieDetail.id, data.id)
    }

    @Test
    fun getTvAiringToday() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.LoadAiringTodayTvShowCallback).onAllAiringTodayTvShowReceived(
                    tvShowResponse
                )
                null
            }.`when`(remote).getAiringTodayTvShow(any())
        }

        val data = LiveDataTestUtil.getValue(catalogueRepository.getTvAiringToday())
        runBlocking {
            verify(remote).getAiringTodayTvShow(any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getDetailTvAiringToday() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as RemoteDataSource.LoadDetailAiringTodayTvShowCallback).onDetailAiringTodayTvShowReceived(
                    tvShowDetail
                )
                null
            }.`when`(remote).getDetailAiringTodayTvShow(eq(tvShowId), any())
        }
        val data = LiveDataTestUtil.getValue(catalogueRepository.getDetailTvAiringToday(tvShowId))

        runBlocking {
            verify(remote).getDetailAiringTodayTvShow(eq(tvShowId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowDetail.id, data.id)
    }
}