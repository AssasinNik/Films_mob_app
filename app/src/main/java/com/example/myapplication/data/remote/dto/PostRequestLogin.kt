package com.example.myapplication.data.remote.dto

data class PostRequestLogin(
    val email: String,
    val parol_user: String,
    val new_parol: String?
)