package com.ryz.moviecatalogue.data.source.remote

import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.ryz.moviecatalogue.network.ApiConfig
import com.ryz.moviecatalogue.utils.EspressoIdlingResource
import com.ryz.moviecatalogue.utils.NetworkInfo.API_KEY
import retrofit2.await

class RemoteDataSource {

    suspend fun getNowPlayingMovies(callback: LoadNowPlayingMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getNowPlayingMovies(API_KEY).await().result?.let { listMovie ->
            callback.onAllNowPlayingMovieReceived(listMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailNowPlayingMovies(
        movieId: Int,
        callback: LoadDetailNowPlayingMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailNowPlayingMovies(movieId, API_KEY).await().let { detailMovie ->
            callback.onDetailNowPlayingMovieReceived(detailMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getAiringTodayTvShow(callback: LoadAiringTodayTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getTvAiringToday(API_KEY).await().result?.let { listTvShow ->
            callback.onAllAiringTodayTvShowReceived(listTvShow)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailAiringTodayTvShow(
        tvShowId: Int,
        callback: LoadDetailAiringTodayTvShowCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailTvAiringToday(tvShowId, API_KEY).await().let { detailTvShow ->
            callback.onDetailAiringTodayTvShowReceived(detailTvShow)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadNowPlayingMoviesCallback {
        fun onAllNowPlayingMovieReceived(moviesResponse: List<MoviesResponse>)
    }

    interface LoadDetailNowPlayingMoviesCallback {
        fun onDetailNowPlayingMovieReceived(moviesDetailResponse: MoviesDetailResponse)
    }

    interface LoadAiringTodayTvShowCallback {
        fun onAllAiringTodayTvShowReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadDetailAiringTodayTvShowCallback {
        fun onDetailAiringTodayTvShowReceived(tvShowDetailResponse: TvShowDetailResponse)
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