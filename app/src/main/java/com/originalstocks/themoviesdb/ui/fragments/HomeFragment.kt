package com.originalstocks.themoviesdb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.originalstocks.themoviesdb.R
import com.originalstocks.themoviesdb.databinding.FragmentHomeBinding
import com.originalstocks.themoviesdb.ui.adapters.PopularMoviesPagingAdapter
import com.originalstocks.themoviesdb.ui.adapters.TVShowsPagingAdapter
import com.originalstocks.themoviesdb.ui.adapters.TrendingDailyPagingAdapter
import com.originalstocks.themoviesdb.ui.adapters.TrendingWeeklyPagingAdapter
import com.originalstocks.themoviesdb.ui.viewModel.MoviesViewModel
import com.originalstocks.themoviesdb.ui.viewModel.TVShowsViewModel
import com.originalstocks.themoviesdb.ui.viewModel.TrendingDailyShowsViewModel
import com.originalstocks.themoviesdb.ui.viewModel.TrendingWeeklyShowsViewModel
import com.originalstocks.themoviesdb.utils.CarousalLayoutManager
import com.originalstocks.themoviesdb.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var carousalLayoutManager1: CarousalLayoutManager
    private lateinit var carousalLayoutManager2: CarousalLayoutManager
    private lateinit var carousalLayoutManager3: CarousalLayoutManager
    private lateinit var carousalLayoutManager4: CarousalLayoutManager
    /*adapters*/
    private val popularMoviesPagingAdapter = PopularMoviesPagingAdapter()
    private val tvShowsPagingAdapter = TVShowsPagingAdapter()
    private val trendingDailyShowsPagingAdapter = TrendingDailyPagingAdapter()
    private val trendingWeeklyShowsPagingAdapter = TrendingWeeklyPagingAdapter()

    /*viewModels*/
    private val moviesViewModel by viewModels<MoviesViewModel>()
    private val tvShowsViewModel by viewModels<TVShowsViewModel>()
    private val trendingDailyShowsViewModel by viewModels<TrendingDailyShowsViewModel>()
    private val trendingWeeklyShowsViewModel by viewModels<TrendingWeeklyShowsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carousalLayoutManager1 = CarousalLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        carousalLayoutManager2 = CarousalLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        carousalLayoutManager3 = CarousalLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        carousalLayoutManager4 = CarousalLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        /** fetching movies via pager flow (The New way)*/
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.pager.collectLatest { data ->
                popularMoviesPagingAdapter.submitData(data)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            tvShowsViewModel.pager.collectLatest { data ->
                tvShowsPagingAdapter.submitData(data)
            }
        }

        trendingDailyShowsViewModel.trendingTodayShowsList.observe(
            viewLifecycleOwner,
            Observer { data ->
                trendingDailyShowsPagingAdapter.submitData(lifecycle, data)
            })

        trendingWeeklyShowsViewModel.trendingWeeklyShowsList.observe(
            viewLifecycleOwner,
            Observer { data ->
                trendingWeeklyShowsPagingAdapter.submitData(lifecycle, data)
            })

        /** Api states for showing progress*/

        popularMoviesPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    //will show progress
                    binding.placeholderMovTextView.text = getString(R.string.fetching_pop_mov)
                }
                is LoadState.Error -> {
                    //will Hide progress
                    binding.placeholderMovTextView.text = getString(R.string.error_message)
                }
                is LoadState.NotLoading -> {
                    //will Hide progress
                    binding.placeholderMovTextView.visibility = View.GONE
                }
            }
        }

        tvShowsPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    //will show progress
                    binding.placeholderTvTextView.text = getString(R.string.fetching_pop_tv)
                }
                is LoadState.Error -> {
                    //will Hide progress
                    binding.placeholderTvTextView.text = getString(R.string.error_message)
                }
                is LoadState.NotLoading -> {
                    //will Hide progress
                    binding.placeholderTvTextView.visibility = View.GONE
                }
            }
        }

        trendingDailyShowsPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    //will show progress
                    binding.placeholderTrendingDailyTextView.text =
                        getString(R.string.daily_fetch_shows)
                }
                is LoadState.Error -> {
                    //will Hide progress
                    binding.placeholderTrendingDailyTextView.text = getString(R.string.error_message)
                    showToast(requireContext(), "Some Error Occurred !")
                }
                is LoadState.NotLoading -> {
                    //will Hide progress
                    binding.placeholderTrendingDailyTextView.visibility = View.GONE
                }
            }
        }

        trendingWeeklyShowsPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    //will show progress
                    binding.placeholderTrendingWeeklyTextView.text =
                        getString(R.string.fetching_weekly_shows)
                }
                is LoadState.Error -> {
                    //will Hide progress
                    binding.placeholderTrendingWeeklyTextView.text = getString(R.string.error_message)
                }
                is LoadState.NotLoading -> {
                    //will Hide progress
                    binding.placeholderTrendingWeeklyTextView.visibility = View.GONE
                }
            }
        }

        /** setting up adapters & [carousalLayoutManager1]*/


        binding.popularMoviesRecyclerView.layoutManager = carousalLayoutManager1
        binding.popularTvShowsRecyclerView.layoutManager = carousalLayoutManager2
        binding.trendingTodayRecyclerView.layoutManager = carousalLayoutManager3
        binding.weeklyTrendingRecyclerView.layoutManager = carousalLayoutManager4

        binding.popularMoviesRecyclerView.adapter = popularMoviesPagingAdapter
        binding.popularTvShowsRecyclerView.adapter = tvShowsPagingAdapter
        binding.trendingTodayRecyclerView.adapter = trendingDailyShowsPagingAdapter
        binding.weeklyTrendingRecyclerView.adapter = trendingWeeklyShowsPagingAdapter

    }

}