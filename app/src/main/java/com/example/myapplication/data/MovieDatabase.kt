package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters

@Database(
    entities = [Movie::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase(){

    abstract val dao: MovieDao
}