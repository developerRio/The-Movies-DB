package com.originalstocks.themoviesdb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.originalstocks.themoviesdb.data.model.Movies
import com.originalstocks.themoviesdb.databinding.MoviesItemsLayoutBinding
import com.originalstocks.themoviesdb.utils.IMAGE_BASE_URL
import com.originalstocks.themoviesdb.utils.MoviesDiffCallback

class PopularMoviesPagingAdapter :
    PagingDataAdapter<Movies, PopularMoviesPagingAdapter.PopularMoviesViewHolder>(MoviesDiffCallback) {
    override fun onBindViewHolder(
        holder: PopularMoviesViewHolder,
        position: Int
    ) {
        val data = getItem(position)
        holder.binding.apply {
            movieNameTextView.text = data?.original_title
            movieRatingsTextView.text = data?.vote_average
            releaseDateTextView.text = data?.release_date
            Glide.with(root).load(IMAGE_BASE_URL + data?.poster_path).into(movieImageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesViewHolder {
        val binding =
            MoviesItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(binding)
    }

    inner class PopularMoviesViewHolder(val binding: MoviesItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}