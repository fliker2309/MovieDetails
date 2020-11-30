package com.example.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class MovieListFragment : Fragment() {
    private var fragmentMovieListClickListener: ClickListenerFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        view?.findViewById<MaterialCardView>(R.id.movie_promo_card)?.setOnClickListener {
            fragmentMovieListClickListener?.toSecondFragment()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerFragment) {
            fragmentMovieListClickListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        fragmentMovieListClickListener = null
    }

    companion object {
        fun newInstance() = MovieListFragment()

        const val TAG = "moviesListFragment"
    }
}
