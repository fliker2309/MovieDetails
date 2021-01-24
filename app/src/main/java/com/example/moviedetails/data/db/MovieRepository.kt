package com.example.moviedetails.data.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun readAllMoviesFromDb(): List<com.example.moviedetails.data.db.entity.Movie> = withContext(Dispatchers.IO){
        movieDao.readMoviesFromDb()
    }

    suspend fun getMovieByIdFromDb(id: Int): com.example.moviedetails.data.db.entity.Movie = withContext(Dispatchers.IO){
        movieDao.getMovieByIdFromDb(id)
    }

    suspend fun insertMoviesInDb(movies: List<com.example.moviedetails.data.db.entity.Movie>) {
        movieDao.insertMoviesInDb(movies)
    }
}
