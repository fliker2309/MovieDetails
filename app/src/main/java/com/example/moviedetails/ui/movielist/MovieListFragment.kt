package com.example.moviedetails.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import com.example.moviedetails.ui.MainActivity
import com.example.moviedetails.ui.movielist.adapter.MovieListAdapter
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieListFragment : Fragment() {

    private var movies: List<Movie> = listOf()
    private var ioScope = CoroutineScope(Dispatchers.IO)

    companion object {
        fun newInstance() = MovieListFragment()
        const val TAG = "moviesListFragment"
    }

    private lateinit var movieListRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {

        }

        //    val movieList = loadMovies(context:Context)// описать суспендфунецию
        val spanCount =
            calculateSpanCount(resources.getDimensionPixelSize(R.dimen.card_view_max_width))
        val movieListAdapter = MovieListAdapter(cardListener = onMoviePromoCardClick())
        val gridLayoutManager = GridLayoutManager(activity, spanCount)
        gridLayoutManager.spanSizeLookup
        movieListRecycler = view.findViewById(R.id.movie_list_recycler_view)
        movieListRecycler.layoutManager = gridLayoutManager
        movieListRecycler.adapter = movieListAdapter
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

    private suspend fun getMovies() = withContext(Dispatchers.IO) {

    }

    private suspend fun getMovie(id: Int): Movie = withContext(Dispatchers.IO) {

    }
}
