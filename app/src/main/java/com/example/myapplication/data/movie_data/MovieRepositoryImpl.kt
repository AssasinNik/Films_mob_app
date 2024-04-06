package com.example.myapplication.data.movie_data

import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl (
    private val dao: MovieDao
): MovieRepository {

    override suspend fun getSelectedMovies(genres: List<String>) {
        dao.getSelectedMovies(genres)
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return dao.getMovieById(id)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies()
    }

}