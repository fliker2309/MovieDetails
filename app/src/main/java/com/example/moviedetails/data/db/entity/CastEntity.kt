package com.example.moviedetails.data.db.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.PrimaryKey

@Entity(
    tableName = "cast_list",
    foreignKeys = [ForeignKey(
        entity = MovieDetailsEntity::class,
        parentColumns = arrayOf("movie_id"),
        childColumns = arrayOf("movie_id"),
        onDelete = SET_DEFAULT
    )]
)
data class CastEntity(
    @PrimaryKey
    @ColumnInfo(name = "cast_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "picture")
    val picture: Bitmap,

    @ColumnInfo(name = "movie_id")
    val movieId: Long
)