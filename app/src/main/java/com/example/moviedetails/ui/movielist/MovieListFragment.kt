package com.example.moviedetails.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.presentation.movielist.MovieListViewModel
import com.example.moviedetails.presentation.movielist.MovieListViewModelFactory
import com.example.moviedetails.ui.movielist.adapter.MovieListAdapter
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMovieListBinding
import com.example.moviedetails.ui.moviedetails.MovieDetailsFragment
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

const val BACKGROUND_UPDATE: String = "Update movies in background"

@InternalCoroutinesApi
class MovieListFragment : Fragment() {

    private val repository: MovieRepository by lazy {
        val db = MovieDatabase.getDatabase(this.requireContext().applicationContext)
        MovieRepository(db.movieDao())
    }

    @InternalCoroutinesApi
    private val movieListViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(repository)
    }

    private var movieListAdapter: MovieListAdapter? = null
    private lateinit var movieListRecycler: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        postponeEnterTransition()

        setUpRecycler()

        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) {
            (binding.movieListRecyclerView.adapter as MovieListAdapter).setMovies(it)
        }

        swipeRefreshLayout.setOnRefreshListener {
            movieListViewModel.getMoviesFromNet()
            movieListViewModel.loadingLiveData.observe(viewLifecycleOwner) {
                swipeRefreshLayout.isRefreshing = it
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun setUpRecycler() {
        movieListAdapter = MovieListAdapter(
            cardListener = onMoviePromoCardClick()
        )

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.star_color)

        movieListRecycler = binding.movieListRecyclerView
        binding.movieListRecyclerView.apply {
            val spanCount =
                calculateSpanCount(resources.getDimensionPixelSize(R.dimen.card_view_max_width))
            movieListRecycler.layoutManager = GridLayoutManager(activity, spanCount)

            val gridLayoutManager = GridLayoutManager(activity, spanCount)
            gridLayoutManager.spanSizeLookup
            movieListRecycler.layoutManager = gridLayoutManager
            movieListRecycler.adapter = movieListAdapter
        }
    }

    private fun onMoviePromoCardClick(): (Int, View) -> Unit = { movieId, promoCardView ->
        exitTransition = MaterialElevationScale(false).apply {
            duration = ANIMATION_DURATION_MILLIS
        }

        reenterTransition = MaterialElevationScale(true).apply {
            duration = ANIMATION_DURATION_MILLIS
        }

        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .addSharedElement(promoCardView, MovieDetailsFragment.MOVIE_SCREEN_TRANSITION_KEY)
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }

    private fun calculateSpanCount(spanWidthPixels: Int): Int {
        val screenWidthPixels = requireContext().resources.displayMetrics.widthPixels
        return (screenWidthPixels / spanWidthPixels + 0.5).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MovieListFragment()
        const val TAG = "moviesListFragment"
        const val ANIMATION_DURATION_MILLIS = 400L
    }
}
