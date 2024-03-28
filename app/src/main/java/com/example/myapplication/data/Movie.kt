package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters

@Entity
data class Movie(
    val title: String?,
    val titleEn: String?,
    val genres: List<String>,
    val posterURL: String,
    val rating: String,
    val length: Int,
    val description: String?,
    val releaseDate: String,
    val ageLimit: String?,
    val trailerLink: String?,
    @PrimaryKey val movieId: Int? = null
)
