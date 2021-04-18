package com.originalstocks.themoviesdb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyTrendingShowsData(
    var page: Int,
    val results: List<DailyTrendingShow>
) : Parcelable

@Parcelize
@Entity
data class DailyTrendingShow(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var media_type: String, // movie & tv
    var original_title: String,
    var release_date: String,
    var first_air_date: String,
    var original_name: String,
    var poster_path: String,
    var vote_average: String,
) : Parcelable
