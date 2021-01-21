package com.example.moviedetails.data.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "movie_details",
    foreignKeys = [ForeignKey(
        entity = MovieListEntity::class,
        parentColumns = arrayOf("movie_id"),
        childColumns = arrayOf("movie_id"),
        onDelete = CASCADE
    )]
)
data class MovieDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "details_id")
    val id: Long,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "pg")
    val pgRating: String,

    @ColumnInfo(name = "runtime")
    val runtime: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "rating")
    val voteAverage: Long,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "movie_id")
    val movieId: Long
)