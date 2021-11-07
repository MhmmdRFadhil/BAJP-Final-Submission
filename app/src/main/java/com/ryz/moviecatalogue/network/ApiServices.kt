package com.ryz.moviecatalogue.network

import com.ryz.moviecatalogue.data.source.remote.response.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.ryz.moviecatalogue.utils.NetworkInfo.API_KEY
import retrofit2.Call
import retrofit2.http.Path

interface ApiServices {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<ListResponse<MoviesResponse>>

    @GET("movie/{movie_id}")
    fun getDetailNowPlayingMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MoviesDetailResponse>

    @GET("tv/airing_today")
    fun getTvAiringToday(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<ListResponse<TvShowResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvAiringToday(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<TvShowDetailResponse>
}