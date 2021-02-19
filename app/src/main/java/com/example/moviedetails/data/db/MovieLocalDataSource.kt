package com.example.moviedetails.data.db

import com.example.moviedetails.data.db.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSource(private val movieDao: MovieDao) {

    suspend fun readAllMoviesFromDb(): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.readMoviesFromDb()
    }

    suspend fun getMovieByIdFromDb(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.getMovieByIdFromDb(id)
    }

    suspend fun insertMoviesInDb(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.insertMoviesInDb(movies)
    }
}



