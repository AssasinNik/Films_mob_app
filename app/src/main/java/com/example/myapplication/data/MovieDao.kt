package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE filmId = :id")
    suspend fun getMovieById(id: Int) : Movie?

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>
}