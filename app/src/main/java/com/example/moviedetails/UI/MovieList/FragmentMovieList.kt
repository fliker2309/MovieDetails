package com.example.moviedetails.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.ui.movielist.adapter.MovieListAdapter
import com.example.moviedetails.R
import com.example.moviedetails.data.DataContainer

import com.example.moviedetails.ui.moviedetails.ClickListenerFragment


class MovieListFragment : Fragment() {

    private var fragmentMovieListClickListener: ClickListenerFragment? = null
    private lateinit var movieListRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieList = DataContainer.getAllMovies()
        val movieListAdapter = MovieListAdapter(
            movies = movieList,
            cardListener = {
                fragmentMovieListClickListener?.toSecondFragment()
            }
        )
        val gridLayoutManager = GridLayoutManager(context, 2)
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        movieListRecycler = view.findViewById(R.id.movie_list_recycler_view)
        movieListRecycler.layoutManager = gridLayoutManager
        movieListRecycler.adapter = movieListAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
