package com.example.moviedetails.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list")
data class MovieListEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "pg")
    val adult: Boolean,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "rating")
    val voteAverage: Long,
    @ColumnInfo(name = "vote_count")
    val voteCount: Long
)

//genres in another table,foreign keys to connect to film