package com.ryz.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ryz.moviecatalogue.data.source.entity.DetailEntity
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    override fun getNowPlayingMovies(): LiveData<List<CatalogueEntity>> {
        val listMovie = MutableLiveData<List<CatalogueEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getNowPlayingMovies(object :
                RemoteDataSource.LoadNowPlayingMoviesCallback {
                override fun onAllNowPlayingMovieReceived(moviesResponse: List<MoviesResponse>) {
                    val movieList = ArrayList<CatalogueEntity>()
                    for (response in moviesResponse) {
                        with(response) {
                            val movie = CatalogueEntity(
                                id,
                                title,
                                posterPath
                            )
                            movieList.add(movie)
                        }
                    }
                    listMovie.postValue(movieList)
                }
            })
        }
        return listMovie
    }

    override fun getDetailNowPlayingMovies(movieId: Int): LiveData<DetailEntity> {
        val movieDetail = MutableLiveData<DetailEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailNowPlayingMovies(
                movieId,
                object : RemoteDataSource.LoadDetailNowPlayingMoviesCallback {
                    override fun onDetailNowPlayingMovieReceived(moviesDetailResponse: MoviesDetailResponse) {
                        with(moviesDetailResponse) {
                            val listGenre = ArrayList<String>()

                            genres.forEach {
                                listGenre.add(it.name)
                            }

                            val movie = DetailEntity(
                                id,
                                title,
                                releaseDate,
                                voteAverage,
                                runtime,
                                listGenre,
                                overview,
                                posterPath
                            )
                            movieDetail.postValue(movie)
                        }
                    }
                })
        }
        return movieDetail

    }


    override fun getTvAiringToday(): LiveData<List<CatalogueEntity>> {
        val listTvShow = MutableLiveData<List<CatalogueEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getAiringTodayTvShow(object :
                RemoteDataSource.LoadAiringTodayTvShowCallback {
                override fun onAllAiringTodayTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<CatalogueEntity>()
                    for (response in tvShowResponse) {
                        with(response) {
                            val tvShow = CatalogueEntity(
                                id,
                                name,
                                posterPath
                            )

                            tvShowList.add(tvShow)
                        }
                        listTvShow.postValue(tvShowList)
                    }
                }
            })
        }
        return listTvShow
    }

    override fun getDetailTvAiringToday(tvShowId: Int): LiveData<DetailEntity> {
        val tvShowDetail = MutableLiveData<DetailEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailAiringTodayTvShow(
                tvShowId,
                object : RemoteDataSource.LoadDetailAiringTodayTvShowCallback {
                    override fun onDetailAiringTodayTvShowReceived(tvShowDetailResponse: TvShowDetailResponse) {
                        with(tvShowDetailResponse) {
                            val listGenre = ArrayList<String>()
                            genres.forEach {
                                listGenre.add(it.name)
                            }

                            val tvShow = if (episodeRunTime.isEmpty()) {
                                DetailEntity(
                                    id,
                                    name,
                                    firstAirDate,
                                    voteAverage,
                                    0,
                                    listGenre,
                                    overview,
                                    posterPath
                                )
                            } else {
                                DetailEntity(
                                    id,
                                    name,
                                    firstAirDate,
                                    voteAverage,
                                    episodeRunTime[0],
                                    listGenre,
                                    overview,
                                    posterPath
                                )
                            }
                            tvShowDetail.postValue(tvShow)
                        }
                    }
                })
        }
        return tvShowDetail
    }

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource)
            }
    }
}
