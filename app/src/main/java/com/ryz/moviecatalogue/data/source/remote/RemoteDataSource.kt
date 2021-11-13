package com.ryz.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.ryz.moviecatalogue.network.ApiConfig
import com.ryz.moviecatalogue.utils.EspressoIdlingResource
import com.ryz.moviecatalogue.utils.NetworkInfo.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class RemoteDataSource {

    fun getNowPlayingMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.instance.getNowPlayingMovies(API_KEY).await().result?.let { listMovie ->
                withContext(Dispatchers.Main) {
                    resultMovies.value = ApiResponse.success(listMovie)
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultMovies
    }

    fun getDetailNowPlayingMovies(movieId: Int): LiveData<ApiResponse<MoviesDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultMovieDetail = MutableLiveData<ApiResponse<MoviesDetailResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.instance.getDetailNowPlayingMovies(movieId, API_KEY).await()
                .let { listDetailMovie ->
                    withContext(Dispatchers.Main) {
                        resultMovieDetail.value = ApiResponse.success(listDetailMovie)
                        EspressoIdlingResource.decrement()
                    }
                }
        }

        return resultMovieDetail
    }

    fun getAiringTodayTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.instance.getTvAiringToday(API_KEY).await().result?.let { listTvShow ->
                withContext(Dispatchers.Main) {
                    resultTvShow.value = ApiResponse.success(listTvShow)
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultTvShow
    }

    fun getDetailAiringTodayTvShow(tvShowId: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShowDetail = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.instance.getDetailTvAiringToday(tvShowId, API_KEY).await()
                .let { listDetailMovie ->
                    withContext(Dispatchers.Main) {
                        resultTvShowDetail.value = ApiResponse.success(listDetailMovie)
                        EspressoIdlingResource.decrement()
                    }
                }
        }
        return resultTvShowDetail
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}