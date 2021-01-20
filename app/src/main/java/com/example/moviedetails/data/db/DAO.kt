package com.example.moviedetails.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedetails.data.model.Details
import com.example.moviedetails.data.model.Movies

@Dao
interface DAO {

    @Query("SELECT * FROM movie_list")
    fun readMovies(): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: Movies)

    @Query("SELECT * FROM movie_details")
    fun readDetails(): LiveData<Details>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: Details)
}

