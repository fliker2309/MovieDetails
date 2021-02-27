package com.example.moviedetails.ui

import android.app.NotificationManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.services.SynchronizationWorker
import com.example.moviedetails.services.WorkRepository
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment
import com.example.moviedetails.ui.movielist.BACKGROUND_UPDATE
import com.example.moviedetails.ui.movielist.MovieListFragment

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                BACKGROUND_UPDATE,
                ExistingPeriodicWorkPolicy.REPLACE,
                WorkRepository().constraintsRequest
            )

        if (savedInstanceState == null) {
            openMoviesList()
            intent?.let(::handleIntent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toIntOrNull()
                movieId?.let {
                    openMovieDetails(movieId)
                    hideNotification(movieId)
                }
            }
        }
    }

    private fun hideNotification(movieId: Int) {
        val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(applicationContext)
        notificationManagerCompat.cancel(movieId)
    }

    private fun openMoviesList() {
        val movieListFragment = MovieListFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, movieListFragment, MovieListFragment.TAG)
            .commit()
    }

    private fun openMovieDetails(movieId: Int) {
        supportFragmentManager.popBackStack(
            MovieListFragment.TAG,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        val movieDetailsFragment = MovieDetailsFragment.newInstance(movieId)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, movieDetailsFragment, MovieListFragment.TAG)
            .addToBackStack(MovieDetailsFragment.TAG)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
        val channel = NotificationChannelCompat
            .Builder(SynchronizationWorker.CHANNEL_NEW_MOVIES, NotificationManager.IMPORTANCE_HIGH)
            .setName("New movies")
            .setDescription("MovieApp movies update")
            .build()

        notificationManagerCompat.createNotificationChannel(channel)
    }
}


