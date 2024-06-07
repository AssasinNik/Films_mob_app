package com.example.myapplication.data.user_data

import androidx.room.Query
import androidx.room.Upsert

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun getUser(): User?

    suspend fun deleteUser()
}