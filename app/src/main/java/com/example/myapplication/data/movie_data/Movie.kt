package com.example.myapplication.data.movie_data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters

@Entity(tableName = "movies")
data class Movie(
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
