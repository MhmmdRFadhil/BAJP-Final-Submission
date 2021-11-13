package com.ryz.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE id =:id")
    fun getMoviesById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id =:id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFav = 1")
    fun getFavTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Update
    fun updateMovies(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(movieEntity: List<TvShowEntity>)

    @Update
    fun updateTvShow(movieEntity: TvShowEntity)
}