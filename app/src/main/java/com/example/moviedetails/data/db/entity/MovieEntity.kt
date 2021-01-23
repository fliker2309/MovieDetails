package com.example.moviedetails.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "pg")
    val pgRating: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "rating")
    val voteAverage: Long?,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long?,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "runtime")
    val runtime: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

//сконвертировать в строку при записи в дао
    @ColumnInfo(name = "genre")
    val genre: String?,

    @Embedded
    val actor: Actor?
)

data class Actor(
    @ColumnInfo(name = "actor_id")
    val actorId: Long?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "profile_path")
    val profilePath: String? = null
)