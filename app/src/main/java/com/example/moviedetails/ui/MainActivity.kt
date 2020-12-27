package com.example.moviedetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedetails.ui.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var movieListFragment: MovieListFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

