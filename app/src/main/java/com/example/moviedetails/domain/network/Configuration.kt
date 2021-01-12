package com.example.moviedetails.domain.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    @SerialName("images")
    val images: Images
)

 @Serializable
 data class Images(
    @SerialName("base_url")
    val baseUrl: String,

    @SerialName("secure_base_url")
    val secureBaseUrl: String,

    @SerialName("backdrop_sizes")
    val backdropSizes: List<String>,

    @SerialName("poster_sizes")
    val posterSizes: List<String>,

    @SerialName("profile_sizes")
    val profileSizes: List<String>
)