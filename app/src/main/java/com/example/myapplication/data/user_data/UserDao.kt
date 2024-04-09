package com.example.myapplication.data.user_data

import androidx.room.Insert
import androidx.room.Upsert

interface UserDao {
    @Upsert
    suspend fun insertUser(user: User)
}