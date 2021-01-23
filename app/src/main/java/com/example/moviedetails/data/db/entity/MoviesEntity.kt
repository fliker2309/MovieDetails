package com.example.moviedetails.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedetails.data.model.Genre

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "pg")
    val pgRating: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "rating")
    val voteAverage: Long,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "runtime")
    val runtime: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @Embedded
    val genres: Genre


)