package com.example.myapplication.data.movie_data

import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl (
    private val movieDao: MovieDao
): MovieRepository {

    /*override suspend fun getSelectedMovies(genres: List<String>) {
        dao.getSelectedMovies(genres)
    }*/

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDao.getMovieById(id)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

}