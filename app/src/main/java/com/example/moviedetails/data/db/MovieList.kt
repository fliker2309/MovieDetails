package com.example.moviedetails.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list")
data class MovieList(
    @PrimaryKey
    val id : Long,
    val title: String,
    val adult: Boolean,
    val posterPath: String,
    val voteAverage: Long,
    val voteCount: Long
)

//genres in another table,foreign keys to connect to film