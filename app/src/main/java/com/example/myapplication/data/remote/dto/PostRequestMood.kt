package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestMood(
    val token : String,
    val genres : Array<String>
)
