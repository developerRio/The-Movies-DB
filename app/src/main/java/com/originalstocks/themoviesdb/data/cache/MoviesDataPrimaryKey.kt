package com.originalstocks.themoviesdb.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesDataPrimaryKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val previous: Int?,
    val next: Int?
)
