package com.originalstocks.themoviesdb.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShow
import com.originalstocks.themoviesdb.data.repository.TrendingWeeklyShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingWeeklyShowsViewModel
@Inject
constructor(private val trendingWeeklyShowsRepository: TrendingWeeklyShowsRepository) :
    ViewModel() {
    val trendingWeeklyShowsList: LiveData<PagingData<WeeklyTrendingShow>> =
        trendingWeeklyShowsRepository.getWeeklyTrendingShows()
}