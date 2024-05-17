package com.example.myapplication.data.remote.dto

data class PostRequestImage(
    val image: ByteArray,
    val token: String,
    val fileName: String
)
