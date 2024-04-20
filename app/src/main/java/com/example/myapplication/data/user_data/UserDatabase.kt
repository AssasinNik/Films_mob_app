package com.example.myapplication.data.user_data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (
    entities = [User::class],
    exportSchema = false,
    version = 1
)
abstract class UserDatabase: RoomDatabase(){

    abstract val userDao: UserDao
}