package com.example.moviedetails.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentManager
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment
import com.example.moviedetails.ui.movielist.MovieListFragment

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        createNotificationChannel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openMoviesList()
            intent?.let { ::handleIntent }
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
                }
            }
        }
    }

    private fun openMoviesList() {
        val movieListFragment = MovieListFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view_tag, movieListFragment, MovieListFragment.TAG)
            .commit()
    }


    private fun openMovieDetails(movieId: Int) {
        supportFragmentManager.popBackStack(
            MovieListFragment.TAG,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        val movieDetailsFragment = MovieDetailsFragment.newInstance(movieId)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view_tag, movieDetailsFragment, MovieListFragment.TAG)
            .addToBackStack(MovieDetailsFragment.TAG)
            .commit()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channelName = getString(R.string.channel_name_text)
        val channelDescription = getString(R.string.channel_description_text)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }
        NotificationManagerCompat.from(applicationContext).createNotificationChannel(channel)

    }


}


