package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val title: String? = null,
    val titleEn: String? = null,
    val genres: List<String>,
    val posterURL: String,
    val rating: String,
    val length: Int,
    val description: String? = null,
    val releaseDate: String,
    val ageLimit: String? = null,
    val trailerLink: String? = null,
    @PrimaryKey val movieId: Int? = null
)
