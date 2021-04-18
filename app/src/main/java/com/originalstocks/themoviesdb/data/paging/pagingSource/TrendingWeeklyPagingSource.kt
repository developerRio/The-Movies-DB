package com.originalstocks.themoviesdb.data.paging.pagingSource

import androidx.paging.PagingSource
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShow
import com.originalstocks.themoviesdb.data.network.RequestInterface
import com.originalstocks.themoviesdb.utils.API_KEY
import com.originalstocks.themoviesdb.utils.STARTING_INDEX
import retrofit2.HttpException
import java.io.IOException

class TrendingWeeklyPagingSource(val requestInterface: RequestInterface) :
    PagingSource<Int, WeeklyTrendingShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WeeklyTrendingShow> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val data = requestInterface.getWeeklyTrendingDataAsync("all", "week", API_KEY, position)
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
}