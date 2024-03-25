package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieById(id: Int) : Movie?

    fun getMovies(): Flow<List<Movie>>
}