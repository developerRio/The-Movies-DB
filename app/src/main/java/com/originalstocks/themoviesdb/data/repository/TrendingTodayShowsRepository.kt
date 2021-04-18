package com.originalstocks.themoviesdb.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.originalstocks.themoviesdb.data.model.DailyTrendingShow
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.data.paging.pagingSource.TrendingTodayPagingSource

class TrendingTodayShowsRepository(val requestInterface: RequestInterface) {
    fun getDailyTrendingShows(): LiveData<PagingData<DailyTrendingShow>> = Pager(
        config = PagingConfig(
            20,
            5,
            false
        ),
        pagingSourceFactory = { TrendingTodayPagingSource(requestInterface) }
    ).liveData
}