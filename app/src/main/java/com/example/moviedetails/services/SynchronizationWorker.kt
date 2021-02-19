package com.example.moviedetails.services

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.MovieLocalDataSource
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.getMoviesList
import com.example.moviedetails.ui.DeepLinks
import com.example.moviedetails.ui.MainActivity
import com.example.moviedetails.ui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class SynchronizationWorker(
    context: Context,
    params: WorkerParameters
) :
    CoroutineWorker(context, params) {

    private val repository =
        MovieLocalDataSource(MovieDatabase.getDatabase(context).movieDao())
    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(applicationContext)


    @ExperimentalSerializationApi
    override suspend fun doWork(): Result = try {

        Log.d("WorkManager", "Connected to internet,wait for fetch movies")
        val netMovies = getMoviesList()
        val differenceMovies = calculateNewMovies(netMovies)
        repository.insertMoviesInDb(netMovies)

        val maxRatedFilm = differenceMovies.maxByOrNull { it.ratings }
        maxRatedFilm?.let { showNotification(it) }
        Log.e("WorkManager", "Update was successful")
        Result.success()


    } catch (e: Throwable) {
        Log.d("WorkManager", "Can't fetch movies: $e")
        Result.failure()
    }

    private fun showNotification(movie: Movie) {
        notificationManagerCompat.notify(movie.id, createNotification(movie))
    }

    private fun createNotification(movie: Movie): Notification {
        val intent = createPendingIntent(movie.id)

        return NotificationCompat.Builder(applicationContext, CHANNEL_NEW_MOVIES)
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOnlyAlertOnce(true)
            .setContentIntent(intent)
            .build()
    }

    private fun createPendingIntent(movieId: Int): PendingIntent {
        val movieDetailsDeepLink: Uri = DeepLinks.getMovieDetailsDeepLink(movieId)

        return PendingIntent.getActivity(
            applicationContext,
            REQUEST_CONTENT,
            Intent(applicationContext, MainActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(movieDetailsDeepLink),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private suspend fun calculateNewMovies(movies: List<Movie>): List<Movie> =
        withContext(Dispatchers.IO) {
            val moviesFromDb: List<Movie> = repository.readAllMoviesFromDb()

            val moviesFromDbIds: List<Int> = moviesFromDb.map { it.id }

            return@withContext movies.filter { movieFromNet ->
                movieFromNet.id !in moviesFromDbIds
            }
        }

    companion object {
        const val CHANNEL_NEW_MOVIES = "New movies"

        const val REQUEST_CONTENT = 1
    }
}


