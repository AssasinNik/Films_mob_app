package com.example.myapplication.data.new_movie_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.movie_data.MovieDao
import com.example.myapplication.util.Converters

@Database(
    entities = [NewMovie::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewMovieDatabase: RoomDatabase() {

    abstract val newMovieDao: NewMovieDao
}