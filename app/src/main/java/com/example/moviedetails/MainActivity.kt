package com.example.moviedetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), ClickListenerFragment {

    private lateinit var movieListFragment: MovieListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            movieListFragment = MovieListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, movieListFragment,MovieListFragment.TAG)
                .commit()
        } else {
            movieListFragment =
                supportFragmentManager.findFragmentByTag(MovieListFragment.TAG) as MovieListFragment
        }

    }

    override fun toSecondFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, MovieDetailsFragment())
            .commit()

    }

}

