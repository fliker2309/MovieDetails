package com.example.moviedetails.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    @SerialName("genres")
    val genres : List<Genre>
)
@Serializable
data class Genre(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)