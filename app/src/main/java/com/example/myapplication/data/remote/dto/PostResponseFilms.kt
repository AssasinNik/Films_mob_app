package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

// Базовый класс для всех ответов, связанных с фильмами
@Serializable
sealed class FilmResponse

// Класс для успешного ответа со списком фильмов
@Serializable
data class FilmListResponse(
    val data: List<Film>?,
    val message: String?,
    val statusCode: StatusCode
) : FilmResponse()

// Класс для ответа с ошибкой, специфичной для запросов фильмов
@Serializable
data class FilmErrorResponse(
    val exception: String?,
    val message: String,
    val statusCode: StatusCode
) : FilmResponse()

// Класс для хранения информации о фильме
@Serializable
data class Film(
    val movie_id: Int,
    val title: String?,
    val titleEn: String?,
    val genres: List<String>,
    val posterURL: String,
    val rating: Double,
    val length: Int,
    val description: String?,
    val releaseDate: Int,
    val ageLimit: Int
)

