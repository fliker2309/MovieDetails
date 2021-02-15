package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import coil.load
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModel
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModelFactory
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMoviesDetailsBinding
import com.example.moviedetails.ui.moviedetails.adapter.ActorAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieDetailsFragment : Fragment() {

    @InternalCoroutinesApi
    private val repository: MovieRepository by lazy {
        val db = MovieDatabase.getDatabase(this.requireContext().applicationContext)
        MovieRepository(db.movieDao())
    }

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(repository)
    }

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding: FragmentMoviesDetailsBinding
        get() = _binding!!

    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)

        val movieId = arguments?.getInt(MOVIE_ID_KEY)
        binding.backToMainButton.setOnClickListener {
            activity?.onBackPressed()
        }

        movieDetailsViewModel.getMovie(movieId!!)
        initRecycler()
        movieDetailsViewModel.movieLiveData.observe(viewLifecycleOwner) { movie: Movie ->
            showMovieData(movie)
        }

        return binding.root
    }

    private fun showMovieData(movie: Movie) {
        binding.apply {
            backToMainButton.visibility = View.VISIBLE
            background.load(movie.backdrop)
            minimumAge.text = requireContext().getString(
                R.string.pg_rating,
                movie.minimumAge.toString()
            )
            movieTitle.text = movie.title
            runtime.text = resources.getString(
                R.string.movie_duration,
                movie.runtime.toString()
            )
            genre.text = movie.genres.joinToString { it.name }
            ratingBar.visibility = View.VISIBLE
            ratingBar.rating = convertRating(movie.ratings)
            totalReviews.text =
                getString(R.string.total_reviews, movie.numberOfRatings.toString())
            storyLineLabel.visibility = View.VISIBLE
            overview.text = movie.overview
            castLabel.visibility = View.VISIBLE
            (actorListRecyclerView.adapter as ActorAdapter).updateActors(movie.actors)
        }
    }

    private fun initRecycler() {
        binding.actorListRecyclerView.apply {
            adapter = ActorAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_ID_KEY to movieId)
        }
    }
}






