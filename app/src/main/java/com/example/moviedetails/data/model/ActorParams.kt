package com.example.moviedetails.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorParams(
    @SerialName("file_path")
    val filePath: String,

    @SerialName("height")
    val height: Int,

    @SerialName("width")
    val width: Int

)