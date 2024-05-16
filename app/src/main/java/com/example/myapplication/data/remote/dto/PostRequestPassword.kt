package com.example.myapplication.data.remote.dto

data class PostRequestPassword(
    val email: String,
    val parol_user: String,
    val new_parol: String?,
    val token: String
)
