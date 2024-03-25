package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val title: String,
    val genres: List<String>,
    val posterURL: String,
    val rating: String,
    val length: Int,
    val description: String,
    @PrimaryKey val movieId: Int? = null
)
