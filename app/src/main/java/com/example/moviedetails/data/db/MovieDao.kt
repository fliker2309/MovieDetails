package com.example.moviedetails.data.db

import androidx.room.*
import com.example.moviedetails.data.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun readMoviesFromDb(): List<MovieEntity>
    /*LiveData<List<Movie>>*///!!!!!!!!!!!!!!!!! read from db

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getMovieByIdFromDb(id: Int): MovieEntity

    @Update
    suspend fun updateMovieInDb(movieEntity: MovieEntity)

    //add data in DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesInDb(movies: List<MovieEntity>)


}

