package com.example.myapplication.data.movie_data

import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getSelectedMovies(genres: List<String>)

    suspend fun getMovieById(id: Int) : Movie?

    fun getMovies(): Flow<List<Movie>>
}