package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class PostResponseWrapper(
    val data: PostResponseUser,  // Вложенный объект данных пользователя
    val message: String?,        // Сообщение, опциональное поле
    val statusCode: StatusCode   // Статус ответа
)

@Serializable
data class StatusCode(
    val value: Int,
    val description: String
)

@Serializable
data class PostResponseUser(
    val id: Int,
    val username: String?,
    val email: String,
    val parol: String?=null,
    var token: String?=null,
    val image: String?
)