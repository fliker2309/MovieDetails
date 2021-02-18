package com.example.moviedetails.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedetails.data.model.Actor
import com.example.moviedetails.data.model.Genre

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "backdrop")
    val backdrop: String,

    @ColumnInfo(name = "rating")
    val ratings: Float,

    @ColumnInfo(name = "vote_count")
    val numberOfRatings: Int,

    @ColumnInfo(name = "minimumAge")
    val minimumAge: Int,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "genres")
    val genres: List<Genre>,

    @ColumnInfo(name = "actors")
    val actors: List<Actor>,

    @ColumnInfo(name = "last_update")
    val lastUpdate : Long = System.currentTimeMillis()
)

