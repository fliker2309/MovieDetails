package com.example.moviedetails.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import com.example.moviedetails.domain.MovieInteractor
import com.example.moviedetails.presentation.movielist.MovieListViewModel
import com.example.moviedetails.presentation.movielist.MovieListViewModelFactory
import com.example.moviedetails.ui.movielist.adapter.MovieListAdapter
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMovieListBinding
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment
import kotlinx.coroutines.*

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(MovieInteractor(requireContext()))
    }
    private lateinit var movieListRecycler: RecyclerView
    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    companion object {
        fun newInstance() = MovieListFragment()
        const val TAG = "moviesListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var movies: List<Movie>




        movieListRecycler = view.findViewById(R.id.movie_list_recycler_view)
        val movieListEmpty = view.findViewById<TextView>(R.id.empty_recycler_text_view)

        CoroutineScope(Dispatchers.IO).launch {
            movies = loadMovies(requireContext())
            setMovieListVisible(movies, movieListRecycler, movieListEmpty)
        }
    }

    private suspend fun setMovieListVisible(
        movies: List<Movie>,
        movieListRecyclerView: RecyclerView,
        movieListEmpty: TextView
    ) = withContext(Dispatchers.Main) {
        if (movies.isNotEmpty()) {
            movieListRecyclerView.visibility = View.VISIBLE
            movieListEmpty.visibility = View.GONE
            val spanCount =
                calculateSpanCount(resources.getDimensionPixelSize(R.dimen.card_view_max_width))
            val movieListAdapter = MovieListAdapter(movies = movies, onMoviePromoCardClick())
            val gridLayoutManager = GridLayoutManager(activity, spanCount)
            gridLayoutManager.spanSizeLookup
            movieListRecycler.layoutManager = gridLayoutManager
            movieListRecycler.adapter = movieListAdapter
        } else {
            movieListRecyclerView.visibility = View.INVISIBLE
            movieListEmpty.visibility = View.VISIBLE
        }
    }

    private fun onMoviePromoCardClick(): (Int) -> Unit = { movieId ->
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
