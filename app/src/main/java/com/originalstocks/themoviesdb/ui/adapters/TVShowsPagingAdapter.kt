package com.originalstocks.themoviesdb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.originalstocks.themoviesdb.data.model.TVShow
import com.originalstocks.themoviesdb.databinding.MoviesItemsLayoutBinding
import com.originalstocks.themoviesdb.utils.IMAGE_BASE_URL
import com.originalstocks.themoviesdb.utils.TVShowsDiffCallback

class TVShowsPagingAdapter :
    PagingDataAdapter<TVShow, TVShowsPagingAdapter.TVShowsViewHolder>(TVShowsDiffCallback) {
    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            movieNameTextView.text = data?.original_name
            movieRatingsTextView.text = data?.vote_average
            releaseDateTextView.text = data?.first_air_date
            Glide.with(root).load(IMAGE_BASE_URL + data?.poster_path).into(movieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        val binding =
            MoviesItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(binding)
    }

    inner class TVShowsViewHolder(val binding: MoviesItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}