package com.originalstocks.themoviesdb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowsData(
    var page: Int,
    val results: List<TVShow>
): Parcelable

@Parcelize
@Entity
data class TVShow(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var original_name: String,
    var first_air_date: String,
    var poster_path: String,
    var vote_average: String,
): Parcelable
