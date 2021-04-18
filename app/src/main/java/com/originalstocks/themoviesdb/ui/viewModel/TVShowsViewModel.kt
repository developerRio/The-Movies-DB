package com.originalstocks.themoviesdb.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.originalstocks.themoviesdb.data.cache.TVShowsDao
import com.originalstocks.themoviesdb.data.model.TVShow
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.data.paging.remoteMediators.PopularTVShowsRemoteMediator
import com.originalstocks.themoviesdb.utils.STARTING_INDEX

class TVShowsViewModel
@ViewModelInject
constructor(private val tvShowsDao: TVShowsDao, private val requestInterface: RequestInterface) : ViewModel() {
    @ExperimentalPagingApi
    val pager = Pager(PagingConfig(20), remoteMediator = PopularTVShowsRemoteMediator(tvShowsDao = tvShowsDao, requestInterface, STARTING_INDEX)) {
        tvShowsDao.getAllTVShowsData()
    }.flow
}