package com.example.moviedetails.UI


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedetails.R
import com.example.moviedetails.UI.moviedetails.ClickListenerFragment
import com.example.moviedetails.UI.moviedetails.MovieDetailsFragment
import com.example.moviedetails.UI.movielist.MovieListFragment

class MainActivity : AppCompatActivity(), ClickListenerFragment {

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

    override fun toSecondFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, MovieDetailsFragment())
            .commit()
    }
}

