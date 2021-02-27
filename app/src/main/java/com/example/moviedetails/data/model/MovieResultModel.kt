package com.example.moviedetails.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResultModel(
    @SerialName("results")
    val results: List<MovieModel>
)
