package com.example.moviedetails.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.db.entity.MovieEntity
import com.example.moviedetails.data.model.Details
import com.example.moviedetails.data.model.Movies

@Dao
interface MovieDao {
    //add data in DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun readMovies(): LiveData<List<Movie>>//!!!!!!!!!!!!!!!!! read from db





   /* @Query("SELECT * FROM cast_list WHERE details_id = :id")
    suspend fun insertCast(cast: CastEntity)*/

}

