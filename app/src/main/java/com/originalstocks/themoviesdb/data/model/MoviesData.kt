package com.originalstocks.themoviesdb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesData(
    var page: Int,
    val results: List<Movies>
): Parcelable

@Parcelize
@Entity
data class Movies(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var original_title: String,
    var release_date: String,
    var poster_path: String,
    var vote_average: String,
): Parcelable
