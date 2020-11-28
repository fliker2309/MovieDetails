package com.example.moviedetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), ClickListenerFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MovieListFragment())
            .commit()

    }

    override fun toSecondFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, MovieDetailsFragment())
            .commit()

    }


}

