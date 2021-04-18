package com.originalstocks.themoviesdb.data.network

import com.originalstocks.themoviesdb.data.model.DailyTrendingShowsData
import com.originalstocks.themoviesdb.data.model.MoviesData
import com.originalstocks.themoviesdb.data.model.TVShowsData
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShowsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestInterface {

    @GET("movie/popular")
    suspend fun getPopularMoviesRequestAsync(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): Response<MoviesData>

    @GET("tv/popular")
    suspend fun getPopularTVShowsDataAsync(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): Response<TVShowsData>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingTodayDataAsync(
        @Path("media_type") mediaType: String, // all
        @Path("time_window") timeWindow: String, // day
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): DailyTrendingShowsData

    @GET("trending/{media_type}/{time_window}")
    suspend fun getWeeklyTrendingDataAsync(
        @Path("media_type") mediaType: String, // all
        @Path("time_window") timeWindow: String, // week
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): WeeklyTrendingShowsData

}