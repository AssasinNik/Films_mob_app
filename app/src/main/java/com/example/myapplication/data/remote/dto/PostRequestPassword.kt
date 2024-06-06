package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestPassword(
    val email: String,
    val parol_user: String,
    val new_parol: String?,
    val token: String
)
