package com.example.myapplication.data.user_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Upsert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE userId = 0")
    suspend fun getUser(): User?

    @Query("DELETE FROM users")
    suspend fun deleteUser()
}