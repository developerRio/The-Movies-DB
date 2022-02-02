package com.originalstocks.themoviesdb.data.paging.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.originalstocks.themoviesdb.data.model.DailyTrendingShow
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.utils.API_KEY
import com.originalstocks.themoviesdb.utils.STARTING_INDEX
import retrofit2.HttpException
import java.io.IOException

class TrendingTodayPagingSource(val requestInterface: RequestInterface) :
    PagingSource<Int, DailyTrendingShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DailyTrendingShow> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val data = requestInterface.getTrendingTodayDataAsync("all", "day", API_KEY, position)
            LoadResult.Page(
                data = data.results,
                prevKey = if (params.key == STARTING_INDEX) null else position - 1,
                nextKey = if (data.results.isNullOrEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DailyTrendingShow>): Int? {
        return getRefreshKey(state)
    }
}