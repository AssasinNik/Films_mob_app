package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE movieId = :id")
    suspend fun getMovieById(id: Int) : Movie?

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<Movie>>
}
