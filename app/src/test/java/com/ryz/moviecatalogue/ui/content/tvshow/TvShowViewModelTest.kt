package com.ryz.moviecatalogue.ui.content.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.ryz.moviecatalogue.data.source.CatalogueRepository
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShowAiringToday() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        `when`(catalogueRepository.getTvAiringToday("title_descending")).thenReturn(tvShows)
        val tvShow = tvShowViewModel.getTvShowAiringToday("title_descending").value?.data
        assertNotNull(tvShow)
        assertEquals(10, tvShow?.size)
        tvShowViewModel.getTvShowAiringToday("title_descending").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}