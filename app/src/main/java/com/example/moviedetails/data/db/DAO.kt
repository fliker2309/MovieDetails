package com.example.moviedetails.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedetails.data.db.entity.CastEntity
import com.example.moviedetails.data.db.entity.MoviesEntity
import com.example.moviedetails.data.model.Cast
import com.example.moviedetails.data.model.Details
import com.example.moviedetails.data.model.Movies

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: MoviesEntity)// in data base
    @Query("SELECT * FROM movie_list")
    fun readMovies(): LiveData<List<Movies>>//!!!!!!!!!!!!!!!!! read from db



    @Query("SELECT * FROM movie_details")
    fun readDetails(): LiveData<Details>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: Details)

    @Query("SELECT * FROM cast_list WHERE details_id = :id")
    suspend fun insertCast(cast: CastEntity)

}

