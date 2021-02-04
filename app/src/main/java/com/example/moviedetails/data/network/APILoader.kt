package com.example.moviedetails.data.network

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedetails.data.model.Actor
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.model.Genre
import com.example.moviedetails.data.model.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
private val api = RetrofitConfig.theMovieDbApiService

@ExperimentalSerializationApi
private suspend fun getConfiguration(): Configuration = api.getConfiguration()

@ExperimentalSerializationApi
private suspend fun getMovies(): Movies = api.getMovies()

@ExperimentalSerializationApi
private suspend fun getGenres(): Map<Int, Genre> =
    api.getGenres().genres.map { it.id to it }.toMap()

@ExperimentalSerializationApi
private suspend fun getActors(movieId: Int): List<Actor> {
    return api.getCredits(movieId).cast
        .map { Actor(it.castID ?: 0, it.profilePath ?: "", it.name) }
        .toList()
}

@ExperimentalSerializationApi
private suspend fun getRuntime(movieId: Int): Int = api.getDetails(movieId).runtime

@ExperimentalSerializationApi
suspend fun getMoviesList(): List<Movie> = withContext(Dispatchers.IO) {

    val imagesBaseUrl = getConfiguration().images.secureBaseUrl.dropLast(1)

    val genres: Map<Int, Genre> = getGenres()

    return@withContext getMovies().results.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster = "$imagesBaseUrl/original/${it.posterPath}",
            backdrop = "$imagesBaseUrl/original/${it.backdropPath}",
            ratings = it.voteAverage,
            numberOfRatings = it.voteCount,
            minimumAge = if (it.adult) 16 else 13,
            runtime = getRuntime(it.id),
            genres = it.genreIDS.map { id -> genres.getOrDefault(id, Genre(0, "")) }.toList(),
            actors = getActors(it.id).map { actor -> actor.copy(picture = "$imagesBaseUrl/original/${actor.picture}")
            }
        )

    }

}