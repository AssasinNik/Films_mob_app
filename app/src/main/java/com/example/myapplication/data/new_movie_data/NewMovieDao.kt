package com.example.myapplication.data.new_movie_data

import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.data.movie_data.Movie
import kotlinx.coroutines.flow.Flow

interface NewMovieDao {

    /*@Query("SELECT * FROM movies WHERE genres = :genres")
    suspend fun getSelectedMovies(genres: List<String>)*/
    @Upsert
    suspend fun insertFilms(movie: NewMovie)
    @Query("SELECT * FROM newMovies WHERE movieId = :id")
    suspend fun getMovieById(id: Int) : NewMovie?

    @Query("SELECT * FROM newMovies")
    fun getMovies(): Flow<List<NewMovie>>
}