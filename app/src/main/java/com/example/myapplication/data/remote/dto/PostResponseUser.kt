package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class PostResponseUser(
    val id: Int,
    val username: String?,
    val email: String,
    val parol: String?=null,
    var token: String?=null,
    val image: String?
)