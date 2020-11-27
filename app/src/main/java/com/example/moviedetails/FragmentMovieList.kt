package com.example.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment

class FragmentMovieList : Fragment() {
    private var fragmentMovieListClickListener: MovieListClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        view?.findViewById<ImageView>(R.id.label_background_linear)?.apply {
            setOnClickListener {
                fragmentMovieListClickListener?.toSecondFragment()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieListClickListener){
            fragmentMovieListClickListener =context
        }

    }

    override fun onDetach() {
        super.onDetach()
    fragmentMovieListClickListener=null
    }
}
