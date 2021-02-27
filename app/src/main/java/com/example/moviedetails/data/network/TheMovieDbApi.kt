package com.example.moviedetails.data.network

import com.example.moviedetails.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("configuration")
    suspend fun getConfiguration(): Configuration

    @GET("movie/upcoming")
    suspend fun getMovies(): MovieResultModel

    @GET("genre/movie/list")
    suspend fun getGenres(): Genres

    @GET("movie/{id}/credits")
    suspend fun getCredits(@Path("id") movieId: Int): Credits

    @GET("movie/{id}")
    suspend fun getDetails(@Path("id") movieId: Int): DetailedMovieModel
}