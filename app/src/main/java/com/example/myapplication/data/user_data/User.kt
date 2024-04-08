package com.example.myapplication.data.user_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val name: String?,
    val login: String,
    val password: String,
    @PrimaryKey val userId: String? = null
)