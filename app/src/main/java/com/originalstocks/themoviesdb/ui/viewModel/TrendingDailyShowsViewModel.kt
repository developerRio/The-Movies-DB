package com.originalstocks.themoviesdb.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.originalstocks.themoviesdb.data.model.DailyTrendingShow
import com.originalstocks.themoviesdb.data.repository.TrendingTodayShowsRepository

class TrendingDailyShowsViewModel
@ViewModelInject
constructor(private val trendingTodayShowsRepository: TrendingTodayShowsRepository) : ViewModel() {
    val trendingTodayShowsList: LiveData<PagingData<DailyTrendingShow>> =
        trendingTodayShowsRepository.getDailyTrendingShows()
}