package com.originalstocks.themoviesdb.data.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.originalstocks.themoviesdb.data.model.Movies

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesData(list: List<Movies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSingle(movies: Movies)

    @Query("SELECT * FROM Movies")
    fun getAllMoviesData(): PagingSource<Int, Movies> // paging net source

    @Query("DELETE FROM Movies")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(list: List<MoviesDataPrimaryKey>)

    @Query("SELECT * FROM MoviesDataPrimaryKey WHERE id = :id")
    suspend fun getAllRemoteKey(id: String): MoviesDataPrimaryKey?

    @Query("DELETE FROM MoviesDataPrimaryKey")
    suspend fun deleteAllRemoteKeys()

}