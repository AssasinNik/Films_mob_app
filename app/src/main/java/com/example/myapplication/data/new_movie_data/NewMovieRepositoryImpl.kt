package com.example.myapplication.data.new_movie_data

import androidx.compose.runtime.movableContentOf
import com.example.myapplication.data.movie_data.Movie
import com.example.myapplication.data.movie_data.MovieRepository
import kotlinx.coroutines.flow.Flow

class NewMovieRepositoryImpl (
    private val newMovieDao: NewMovieDao
): NewMovieRepository{
    override suspend fun insertFilms(movie: NewMovie) {
        return newMovieDao.insertFilms(movie)
    }

    override suspend fun getMovieById(id: Int): NewMovie? {
        return newMovieDao.getMovieById(id)
    }

    override fun getMovies(): Flow<List<NewMovie>> {
        return newMovieDao.getMovies()
    }

}