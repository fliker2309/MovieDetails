package com.example.moviedetails.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.presentation.movielist.MovieListViewModel
import com.example.moviedetails.presentation.movielist.MovieListViewModelFactory
import com.example.moviedetails.ui.movielist.adapter.MovieListAdapter
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMovieListBinding
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment

import kotlinx.serialization.ExperimentalSerializationApi

class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory()
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

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMovieListBinding.bind(view)
        movieListViewModel.getMovies()
        movieListRecycler = binding.movieListRecyclerView
        binding.movieListRecyclerView.let {
            val movies : List<Movie> = listOf()
            val spanCount =
                calculateSpanCount(resources.getDimensionPixelSize(R.dimen.card_view_max_width))
            it.layoutManager = GridLayoutManager(activity, spanCount)
            val movieListAdapter = MovieListAdapter(movies = movies, onMoviePromoCardClick())
            val gridLayoutManager = GridLayoutManager(activity, spanCount)
            gridLayoutManager.spanSizeLookup
            movieListRecycler.layoutManager = gridLayoutManager
            movieListRecycler.adapter = movieListAdapter
        }


        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner){
            (binding.movieListRecyclerView.adapter as MovieListAdapter).setMovies(it)
        }
        movieListViewModel.loadingLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
