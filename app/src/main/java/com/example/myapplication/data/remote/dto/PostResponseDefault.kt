package com.example.myapplication.data.remote.dto

data class PostResponseDefault(
    val message: String,
    val exception: String? = null,  // добавим поле для исключений, если они есть
    val statusCode: StatusCode? = null  // добавим статус код для детализации ответа
)