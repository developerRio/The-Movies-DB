package com.originalstocks.themoviesdb.data.paging.remoteMediators

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.originalstocks.themoviesdb.data.cache.TVShowsDao
import com.originalstocks.themoviesdb.data.cache.TVShowsDataPrimaryKey
import com.originalstocks.themoviesdb.data.model.TVShow
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.utils.API_KEY
import java.io.InvalidObjectException

private const val TAG = "PopularTVShowsRemoteMed"
@ExperimentalPagingApi
class PopularTVShowsRemoteMediator(
    private val tvShowsDao: TVShowsDao,
    private val requestInterface: RequestInterface,
    private val initialPage: Int = 1
) : RemoteMediator<Int, TVShow>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, TVShow>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey = getLastRemoteKey(state) ?: throw InvalidObjectException("InvalidObjectException Occurred")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeys(state)
                    remoteKey?.next?.plus(1) ?: initialPage
                }
            }
            val response = requestInterface.getPopularTVShowsDataAsync(
                API_KEY,
                pageNumber = page
            )

            val endOfPagination = response.body()?.results?.size!! < state.config.pageSize
            Log.i(TAG, "load_page_number_ev = $page end_of_paging condition = ${response.body()?.results?.size} < ${state.config.pageSize} " )

            if (response.isSuccessful) {
                response.body()?.let {

                    // flush our data
                    if (loadType == LoadType.REFRESH) {
                        tvShowsDao.deleteAllTVShows()
                        tvShowsDao.deleteAllRemoteKeys()
                    }

                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1

                    val list = response.body()?.results?.map {
                        TVShowsDataPrimaryKey(it.id, prev, next)
                    }
                    // make list of remote keys
                    if (list != null) {
                        tvShowsDao.insertAllTVShowsRemoteKeys(list)
                    }
                    // insert to the room
                    tvShowsDao.insertTVShowsData(it.results)
                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getClosestRemoteKeys(state: PagingState<Int, TVShow>): TVShowsDataPrimaryKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                tvShowsDao.getAllTVShowsRemoteKey(it.id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, TVShow>): TVShowsDataPrimaryKey? {
        return state.lastItemOrNull()?.let {
            tvShowsDao.getAllTVShowsRemoteKey(it.id)
        }
    }
}