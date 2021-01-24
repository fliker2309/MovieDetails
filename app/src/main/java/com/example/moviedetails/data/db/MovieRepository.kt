package com.example.moviedetails.data.db

import androidx.lifecycle.LiveData
import com.example.moviedetails.data.Actor
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.db.MovieDao
import com.example.moviedetails.data.db.entity.MovieEntity
import com.example.moviedetails.data.model.Genre
import com.example.moviedetails.data.model.Movies
import com.example.moviedetails.data.network.Configuration
import com.example.moviedetails.data.network.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

class MovieRepository(private val movieDao: MovieDao) {
    val readAllMoviesFromDb: LiveData<List<Movie>> = movieDao.readMovies()

    suspend fun insertMoviesInDb(movieEntity: MovieEntity) {
        movieDao.insertMoviesInDb(movieEntity)
    }
}
