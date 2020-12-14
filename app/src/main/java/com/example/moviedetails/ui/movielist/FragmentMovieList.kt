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
import com.example.moviedetails.ui.R
import com.example.moviedetails.data.DataContainer
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment


class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
        const val TAG = "moviesListFragment"
    }
    private var fragmentMovieListClickListener: MovieDetailsFragment.ClickListenerFragment? = null
    private lateinit var movieListRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieList = DataContainer.getAllMovies()
        val movieListAdapter = MovieListAdapter(
            movies = movieList,
            cardListener = onMoviePromoCardClick()
        )
        val spanCount =
            calculateSpanCount(resources.getDimensionPixelSize(R.dimen.card_view_max_width))

        val gridLayoutManager = GridLayoutManager(activity, spanCount)
        gridLayoutManager.spanSizeLookup
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
        if (context is MovieDetailsFragment.ClickListenerFragment) {
            fragmentMovieListClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMovieListClickListener = null
    }

    private fun onMoviePromoCardClick(): (Long) -> Unit = { movieId ->
        fragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.add(R.id.main_container, MovieDetailsFragment.newInstance(movieId))
            ?.commit()
    }

    private fun calculateSpanCount(spanWidthPixels: Int): Int {
        val screenWidthPixels = requireContext().resources.displayMetrics.widthPixels
        return (screenWidthPixels / spanWidthPixels + 0.5).toInt()
    }
}
