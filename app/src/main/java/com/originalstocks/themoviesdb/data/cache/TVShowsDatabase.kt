package com.originalstocks.themoviesdb.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.originalstocks.themoviesdb.data.model.TVShow

@Database(
    entities = [TVShow::class, TVShowsDataPrimaryKey::class],
    version = 1,
    exportSchema = false
)
abstract class TVShowsDatabase : RoomDatabase() {
    abstract fun getTVShowsDao(): TVShowsDao
}