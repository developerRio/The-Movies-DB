package com.originalstocks.themoviesdb.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.originalstocks.themoviesdb.data.cache.MoviesDao
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.data.paging.remoteMediators.PopularMoviesRemoteMediator
import com.originalstocks.themoviesdb.utils.STARTING_INDEX
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject
constructor(private val moviesDao: MoviesDao, private val requestInterface: RequestInterface) :
    ViewModel() {


    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        PagingConfig(20),
        remoteMediator = PopularMoviesRemoteMediator(
            moviesDao = moviesDao,
            requestInterface,
            STARTING_INDEX
        )
    ) {
        moviesDao.getAllMoviesData()
    }.flow

}