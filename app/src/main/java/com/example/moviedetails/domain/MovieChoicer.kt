package com.example.moviedetails.domain

import android.content.Context
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

interface MovieChoicer {
    suspend fun getMovies() :List<Movie>

    suspend fun getMovie(id: Int): Movie?

}

class MovieImplicator(
    private val coroutineScope: CoroutineScope,
    private val context : Context
) : MovieChoicer{
    override suspend fun getMovies(): List<Movie> {
        val moviesDeferred = coroutineScope.async {
            loadMovies(context)
        }
        return moviesDeferred.await()

    }

    override suspend fun getMovie(id: Int): Movie? {
        val movieDeferred = coroutineScope.async {
            loadMovies(context).findLast { movie ->
                movie.id == id
            }
        }

        return movieDeferred.await()
    }

}
