package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestUsername(
    val token: String,
    val username: String
)
