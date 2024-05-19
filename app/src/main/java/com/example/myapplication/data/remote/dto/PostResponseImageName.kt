package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseImageName(
    val data: String?,  // предположительно путь или имя загруженного файла
    val message: String,
    val exception: String? = null,
    val statusCode: StatusCode
)
