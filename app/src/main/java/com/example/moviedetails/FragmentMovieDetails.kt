package com.example.moviedetails

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat


class FragmentMovieDetails : Fragment() {
    private var fragmentMovieDetailsClickListener: MovieListClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view?.findViewById<Button>(R.id.back_to_main_button)?.apply {
            setOnClickListener {
                fragmentMovieDetailsClickListener?.toFirstFragment()
            }

        }
        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieListClickListener) {
            fragmentMovieDetailsClickListener = context
        }
    }
    override fun onDetach() {
        super.onDetach()
        fragmentMovieDetailsClickListener=null
    }
}