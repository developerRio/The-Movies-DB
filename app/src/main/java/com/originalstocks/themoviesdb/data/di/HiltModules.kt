package com.originalstocks.themoviesdb.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.originalstocks.themoviesdb.data.cache.MoviesDao
import com.originalstocks.themoviesdb.data.cache.MoviesDatabase
import com.originalstocks.themoviesdb.data.cache.TVShowsDao
import com.originalstocks.themoviesdb.data.cache.TVShowsDatabase
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.data.repository.TrendingTodayShowsRepository
import com.originalstocks.themoviesdb.data.repository.TrendingWeeklyShowsRepository
import com.originalstocks.themoviesdb.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesRequestsInterface(retrofit: Retrofit): RequestInterface {
        return retrofit.create(RequestInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideTrendingTodayShows(requestInterface: RequestInterface): TrendingTodayShowsRepository {
        return TrendingTodayShowsRepository(requestInterface)
    }

    @Singleton
    @Provides
    fun provideTrendingWeeklyShows(requestInterface: RequestInterface): TrendingWeeklyShowsRepository {
        return TrendingWeeklyShowsRepository(requestInterface)
    }

    // caching

    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao {
        return moviesDatabase.getMoviesDao()
    }

    @Singleton
    @Provides
    fun provideTVShowsDatabase(@ApplicationContext context: Context): TVShowsDatabase {
        return Room.databaseBuilder(
            context,
            TVShowsDatabase::class.java,
            "tvshows"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTVShowsDao(tvShowsDatabase: TVShowsDatabase): TVShowsDao {
        return tvShowsDatabase.getTVShowsDao()
    }

}