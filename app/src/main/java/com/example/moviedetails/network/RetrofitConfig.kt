package com.example.moviedetails.network

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create

object RetrofitConfig {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val theMovieDbApiService: TheMovieDbApi = retrofitBuilder.create()
}