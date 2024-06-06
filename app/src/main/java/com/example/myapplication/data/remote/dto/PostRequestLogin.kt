package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequestLogin(
    val email: String,
    val parol_user: String,
    val new_parol: String?=null
)