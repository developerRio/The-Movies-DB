package com.originalstocks.themoviesdb.data.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.originalstocks.themoviesdb.data.model.TVShow

@Dao
interface TVShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShowsData(list: List<TVShow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShowSingle(movies: TVShow)

    @Query("SELECT * FROM TVShow")
    fun getAllTVShowsData(): PagingSource<Int, TVShow> // paging net source

    @Query("DELETE FROM TVShow")
    suspend fun deleteAllTVShows()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTVShowsRemoteKeys(list: List<TVShowsDataPrimaryKey>)

    @Query("SELECT * FROM TVShowsDataPrimaryKey WHERE id = :id")
    suspend fun getAllTVShowsRemoteKey(id: String): TVShowsDataPrimaryKey?

    @Query("DELETE FROM TVShowsDataPrimaryKey")
    suspend fun deleteAllRemoteKeys()
}