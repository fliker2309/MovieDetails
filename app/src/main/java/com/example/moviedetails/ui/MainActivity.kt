package com.example.moviedetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import com.example.moviedetails.ui.movielist.MovieListFragment
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieListFragment: MovieListFragment

    companion object {
        var movies: List<Movie> = listOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val operation = async(Dispatchers.IO) {
                movies = loadMovies(applicationContext)
            }
            operation.await()
        }

        if (savedInstanceState == null) {
            movieListFragment = MovieListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, movieListFragment, MovieListFragment.TAG)
                .commit()
        } else {
            movieListFragment =
                supportFragmentManager.findFragmentByTag(MovieListFragment.TAG) as MovieListFragment
        }
    }
}

