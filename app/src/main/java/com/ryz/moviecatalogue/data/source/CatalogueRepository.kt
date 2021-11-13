package com.ryz.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ryz.moviecatalogue.data.source.local.LocalDataSource
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.data.source.remote.ApiResponse
import com.ryz.moviecatalogue.data.source.remote.RemoteDataSource
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse
import com.ryz.moviecatalogue.utils.AppExecutors
import com.ryz.moviecatalogue.vo.Resource
import java.lang.StringBuilder

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    override fun getNowPlayingMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MoviesResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovie(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getNowPlayingMovies()

            override fun saveCallResult(data: List<MoviesResponse>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    with(response) {
                        val movie = MovieEntity(
                            id,
                            title,
                            posterPath,
                            "",
                            0.0,
                            0,
                            "",
                            "",
                            false
                        )
                        listMovie.add(movie)
                    }
                    localDataSource.insertMovies(listMovie)
                }
            }
        }.asLiveData()
    }

    override fun getDetailNowPlayingMovies(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MoviesDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.duration == 0 && data.genre == ""
                        && data.years == "" && data.score == 0.0 && data.overview == ""

            override fun createCall(): LiveData<ApiResponse<MoviesDetailResponse>> =
                remoteDataSource.getDetailNowPlayingMovies(movieId)

            override fun saveCallResult(data: MoviesDetailResponse) {
                with(data) {
                    val listGenre = StringBuilder().append("")

                    for (i in genres.indices) {
                        if (i < genres.size - 1) {
                            listGenre.append("${genres[i].name}, ")
                        } else {
                            listGenre.append(genres[i].name)
                        }
                    }

                    val movie = MovieEntity(
                        id,
                        title,
                        posterPath,
                        releaseDate,
                        voteAverage,
                        runtime,
                        listGenre.toString(),
                        overview,
                        false
                    )
                    localDataSource.updateMovies(movie, false)
                }
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movieEntity: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovies(movieEntity, state)
        }
    }

    override fun getTvAiringToday(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAiringTodayTvShow()


            override fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    with(response) {
                        val tvShow = TvShowEntity(
                            id,
                            name,
                            posterPath,
                            "",
                            0.0,
                            0,
                            "",
                            ""
                        )
                        listTvShow.add(tvShow)
                    }
                    localDataSource.insertTvShow(listTvShow)
                }
            }
        }.asLiveData()
    }

    override fun getDetailTvAiringToday(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.duration == 0 && data.genre == ""
                        && data.years == "" && data.score == 0.0 && data.overview == ""

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getDetailAiringTodayTvShow(tvShowId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                with(data) {
                    val listGenre = StringBuilder().append("")
                    for (i in genres.indices) {
                        if (i < genres.size - 1) {
                            listGenre.append("${genres[i].name}, ")
                        } else {
                            listGenre.append(genres[i].name)
                        }
                    }
                    val tvShow = if (episodeRunTime.isEmpty()) {
                        TvShowEntity(
                            id,
                            name,
                            posterPath,
                            firstAirDate,
                            voteAverage,
                            0,
                            listGenre.toString(),
                            overview,
                            false
                        )
                    } else {
                        TvShowEntity(
                            id,
                            name,
                            posterPath,
                            firstAirDate,
                            voteAverage,
                            episodeRunTime.first(),
                            listGenre.toString(),
                            overview,
                            false
                        )
                    }
                    localDataSource.updateTvShow(tvShow, false)
                }
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEntity, state)
        }
    }

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
}
