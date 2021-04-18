package com.originalstocks.themoviesdb.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.originalstocks.themoviesdb.data.cache.MoviesDao
import com.originalstocks.themoviesdb.data.model.Movies
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.data.paging.remoteMediators.PopularMoviesRemoteMediator
import com.originalstocks.themoviesdb.utils.STARTING_INDEX

class MoviesViewModel
@ViewModelInject
constructor(private val moviesDao: MoviesDao, private val requestInterface: RequestInterface) : ViewModel() {

    @ExperimentalPagingApi
    val pager = Pager(PagingConfig(20), remoteMediator = PopularMoviesRemoteMediator(moviesDao = moviesDao, requestInterface, STARTING_INDEX)) {
        moviesDao.getAllMoviesData()
    }.flow

}