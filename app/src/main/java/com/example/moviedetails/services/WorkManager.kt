package com.example.moviedetails.services

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.network.getMoviesList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class WorkManager(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val repository =
        MovieRepository(MovieDatabase.getDatabase(applicationContext).movieDao())

    @ExperimentalSerializationApi
    override suspend fun doWork(): Result {
        return try {
            Log.d("WorkManager", "Connected to internet")
            val loadedMovies = getMoviesList()
            repository.insertMoviesInDb(loadedMovies)
            Result.success()

        } catch (e: Exception) {
            Log.d("WorkManager", "Can't fetch movies: $e")
            Result.failure()
        }
    }
}
