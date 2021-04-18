package com.originalstocks.themoviesdb.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.originalstocks.themoviesdb.data.model.Movies
import com.originalstocks.themoviesdb.data.model.TVShow

@Database(
    entities = [Movies::class, MoviesDataPrimaryKey::class], // ,TVShow::class, DailyTrendingShow::class, WeeklyTrendingShow::class
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}