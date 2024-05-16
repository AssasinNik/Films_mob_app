package com.example.myapplication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseNewFilms(
    val movie_id: Int? = null,
    val title: String?,
    val titleEn: String?,
    val genres: List<String>,
    val posterURL: String,
    val rating: Int,
    val length: Int,
    val description: String?,
    val releaseDate: Int,
    val ageLimit: Int
)
