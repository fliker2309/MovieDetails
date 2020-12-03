package com.example.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class MovieListFragment : Fragment() {
    private var fragmentMovieListClickListener: ClickListenerFragment? = null
    private var movieListRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        view?.findViewById<RecyclerView>(R.id.movie_list_recycler_view)?.setOnClickListener {
            fragmentMovieListClickListener?.toSecondFragment()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       movieListRecycler = view.findViewById(R.id.movie_list_recycler_view)
        movieListRecycler?.adapter = MovieListAdapter()
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
