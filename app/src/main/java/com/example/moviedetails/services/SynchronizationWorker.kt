package com.example.moviedetails.services

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviedetails.data.db.MovieDao
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.MovieLocalDataSource
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.calculateNewMovies
import com.example.moviedetails.data.network.getMoviesList
import com.example.moviedetails.ui.DeepLinks
import com.example.moviedetails.ui.MainActivity
import com.example.moviedetails.ui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class SynchronizationWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val repository =
        MovieLocalDataSource(MovieDatabase.getDatabase(context).movieDao())
    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(applicationContext)


    @ExperimentalSerializationApi
    override suspend fun doWork(): Result {
        return try {


            Log.d("WorkManager", "Connected to internet,wait for fetch movies")
            val loadedMovies = getMoviesList()
            calculateNewMovies(loadedMovies)
            repository.insertMoviesInDb(loadedMovies)



            //здесь бы показать уведомление, но я передал список фильма, а нужно показать 1 фильм
            // showNotification(movie) вот так хотелось бы сделать
            Result.success()

        } catch (e: Exception) {
            Log.d("WorkManager", "Can't fetch movies: $e")
            Result.failure()
        }
    }

    private fun showNotification(movie: Movie) {


        val contentUri = DeepLinks.getMovieDetailsDeepLink(movie.id)

        val notification = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_NEW_MOVIES
        )
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext,
                    REQUEST_CONTENT,
                    Intent(applicationContext, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

        notificationManagerCompat.notify(MOVIE_TAG, movie.id, notification)
    }

    private fun dismissNotification(movie: Movie) {
        notificationManagerCompat.cancel(MOVIE_TAG, movie.id)
    }

    companion object {
        const val CHANNEL_NEW_MOVIES = "New movies"

        const val REQUEST_CONTENT = 1

        const val MOVIE_TAG = "movie"
    }

}


