package com.example.myapplication.data.user_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.util.Converters


@Database (
    entities = [User::class],
    exportSchema = false,
    version = 2
)
@TypeConverters(Converters::class)
abstract class UserDatabase: RoomDatabase(){

    abstract val userDao: UserDao
}