package com.example.myapplication.data.new_movie_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newMovies")
data class NewMovie(
    @PrimaryKey val movieId: Int? = null,
    val title: String?,
    val titleEn: String?,
    //val genres: List<String>,
    val genres: String,
    val posterURL: String,
    val rating: Int,
    val length: Int,
    val description: String?,
    val releaseYear: String,
    val ageLimit: String?,
)
