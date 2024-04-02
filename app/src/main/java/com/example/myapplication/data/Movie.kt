package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters

@Entity(tableName = "movies")
data class Movie(
    val title: String,
    val posterURL: String?,
    @PrimaryKey val movieId: Int,
    val description: String?,
    //val titleEn: String?,
    //val genres: List<String>,
    //val rating: Int,
    //val length: Int,
    //val releaseYear: String,
    //val ageLimit: Int,
)
