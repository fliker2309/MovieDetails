package com.example.moviedetails.data.db

import androidx.room.*
import com.example.moviedetails.data.db.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun readMoviesFromDb(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getMovieByIdFromDb(id: Int): Movie

    @Update
    suspend fun updateMovieInDb(movie: Movie)

    //add data in DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesInDb(movies: List<Movie>)
}

