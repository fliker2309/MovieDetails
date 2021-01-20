package com.example.moviedetails.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.PrimaryKey

@Entity(
    tableName = "genres",
    foreignKeys = [ForeignKey(
        entity = MovieListEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movie_id"),
        onDelete = NO_ACTION
    )]
)
data class GenresEntity(
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    val id: Long,
    @ColumnInfo(name = "genre_name")
    val name: String,
    @ColumnInfo(name = "movie_id")
    val movieId: Long
)