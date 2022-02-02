package com.originalstocks.themoviesdb.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.originalstocks.themoviesdb.data.model.DailyTrendingShow
import com.originalstocks.themoviesdb.data.repository.TrendingTodayShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingDailyShowsViewModel
@Inject
constructor(private val trendingTodayShowsRepository: TrendingTodayShowsRepository) : ViewModel() {
    val trendingTodayShowsList: LiveData<PagingData<DailyTrendingShow>> =
        trendingTodayShowsRepository.getDailyTrendingShows()
}