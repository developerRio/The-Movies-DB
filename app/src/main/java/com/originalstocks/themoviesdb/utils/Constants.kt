package com.originalstocks.themoviesdb.utils

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.originalstocks.themoviesdb.data.model.DailyTrendingShow
import com.originalstocks.themoviesdb.data.model.Movies
import com.originalstocks.themoviesdb.data.model.TVShow
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShow

const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
const val API_KEY = "d0cb262143ccde524f467d6cdf6c7e9e"

const val STARTING_INDEX = 1

object MoviesDiffCallback : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}

object TVShowsDiffCallback : DiffUtil.ItemCallback<TVShow>() {
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem == newItem
    }
}

object TrendingDailyDiffCallback : DiffUtil.ItemCallback<DailyTrendingShow>() {
    override fun areItemsTheSame(oldItem: DailyTrendingShow, newItem: DailyTrendingShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DailyTrendingShow,
        newItem: DailyTrendingShow
    ): Boolean {
        return oldItem == newItem
    }
}

object TrendingWeeklyDiffCallback : DiffUtil.ItemCallback<WeeklyTrendingShow>() {
    override fun areItemsTheSame(
        oldItem: WeeklyTrendingShow,
        newItem: WeeklyTrendingShow
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WeeklyTrendingShow,
        newItem: WeeklyTrendingShow
    ): Boolean {
        return oldItem == newItem
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

enum class ApiStatus {
    LOADING, SUCCESS, ERROR
}
