package com.example.myapplication.data.new_movie_data

import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface NewMovieRepository {

    //suspend fun getSelectedMovies(genres: List<String>)

    suspend fun insertFilms(movie: NewMovie)

    suspend fun getMovieById(id: Int) : NewMovie?

    fun getMovies(): Flow<List<NewMovie>>
}