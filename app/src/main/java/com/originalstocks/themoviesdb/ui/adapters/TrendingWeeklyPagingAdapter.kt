package com.originalstocks.themoviesdb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.originalstocks.themoviesdb.data.model.WeeklyTrendingShow
import com.originalstocks.themoviesdb.databinding.MoviesItemsLayoutBinding
import com.originalstocks.themoviesdb.utils.IMAGE_BASE_URL
import com.originalstocks.themoviesdb.utils.TrendingWeeklyDiffCallback

class TrendingWeeklyPagingAdapter :
    PagingDataAdapter<WeeklyTrendingShow, TrendingWeeklyPagingAdapter.TrendingWeeklyViewHolder>(
        TrendingWeeklyDiffCallback
    ) {
    override fun onBindViewHolder(holder: TrendingWeeklyViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            if (data?.media_type == "tv") {
                movieNameTextView.text = data.original_name
                releaseDateTextView.text = data.first_air_date
            } else if (data?.media_type == "movie") {
                movieNameTextView.text = data.original_title
                releaseDateTextView.text = data.release_date
            }
            movieRatingsTextView.text = data?.vote_average
            Glide.with(root).load(IMAGE_BASE_URL + data?.poster_path).into(movieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingWeeklyViewHolder {
        val binding =
            MoviesItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingWeeklyViewHolder(binding)
    }

    inner class TrendingWeeklyViewHolder(val binding: MoviesItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}