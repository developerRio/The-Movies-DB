package com.originalstocks.themoviesdb.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.originalstocks.themoviesdb.data.paging.pagingSource.TrendingWeeklyPagingSource
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShow
import com.originalstocks.themoviesdb.data.network.RequestInterface

class TrendingWeeklyShowsRepository(val requestInterface: RequestInterface) {
    fun getWeeklyTrendingShows(): LiveData<PagingData<WeeklyTrendingShow>> = Pager(
        config = PagingConfig(
            20,
            5,
            false
        ),
        pagingSourceFactory = { TrendingWeeklyPagingSource(requestInterface) }
    ).liveData
}