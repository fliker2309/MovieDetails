package com.example.moviedetails.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString


import androidx.annotation.WorkerThread
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.ui.DeepLinks
import com.example.moviedetails.ui.MainActivity
import com.example.moviedetails.ui.R
import kotlinx.coroutines.InternalCoroutinesApi

interface Notifications {

    fun showNotification(movie: Movie)
    fun dismissNotification(movie: Movie)
}

class MovieNotifications(private val context: Context) : Notifications {

    companion object {
        private const val CHANNEL_NEW_MOVIES = "New movies"

        private const val REQUEST_CONTENT = 1

        private const val MOVIE_TAG = "movie"
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    @InternalCoroutinesApi
    @WorkerThread
    override fun showNotification(movie: Movie) {

        val channel = NotificationChannelCompat
            .Builder(CHANNEL_NEW_MOVIES, IMPORTANCE_HIGH)
            .setName(R.string.channel_new_movies.toString())
            .setDescription(R.string.channel_new_movies_description.toString())
            .build()

        notificationManagerCompat.createNotificationChannel(channel)

        val contentUri = DeepLinks.getMovieDetailsDeepLink(movie.id)

        val notification = NotificationCompat.Builder(context, CHANNEL_NEW_MOVIES)
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CONTENT,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

        notificationManagerCompat.notify(MOVIE_TAG, movie.id, notification)
    }

    override fun dismissNotification(movie: Movie) {
        notificationManagerCompat.cancel(MOVIE_TAG, movie.id)
    }
}
