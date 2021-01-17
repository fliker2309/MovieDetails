package com.example.moviedetails.domain

import android.content.Context
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies

class MovieInteractor(private val context: Context) {
    suspend fun getMoviesList(): List<Movie> {
        return loadMovies(context)
    }
}