package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable

data class PostRequestRegister(
    val email: String,
    val username: String,
    val parol_user: String
)
