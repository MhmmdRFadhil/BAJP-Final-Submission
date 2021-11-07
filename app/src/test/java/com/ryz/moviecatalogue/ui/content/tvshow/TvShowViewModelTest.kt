package com.ryz.moviecatalogue.ui.content.tvshow

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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<CatalogueEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = DataDummy.getTvShow()
        val tvShows = MutableLiveData<List<CatalogueEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvAiringToday()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShowAiringToday().value
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)
        viewModel.getTvShowAiringToday().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}