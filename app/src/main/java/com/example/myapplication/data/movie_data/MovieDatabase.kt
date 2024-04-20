package com.example.myapplication.data.movie_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters

@Database(
    entities = [Movie::class],
    exportSchema = false,
    version = 4
)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase(){

    abstract val movieDao: MovieDao
}