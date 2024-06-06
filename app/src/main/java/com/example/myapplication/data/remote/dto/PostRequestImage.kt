package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestImage(
    val filePath: String,
    val token: String
)
