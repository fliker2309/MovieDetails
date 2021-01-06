package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.MovieInteractor
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModel
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModelFactory
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMoviesDetailsBinding
import com.example.moviedetails.ui.moviedetails.adapter.MovieDetailsAdapter


class MovieDetailsFragment : Fragment() {

    companion object {
        private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_ID_KEY to movieId)
        }
    }

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieInteractor(requireContext()))
    }

    private var selectedMovieID: Int = 0

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding: FragmentMoviesDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMainButton.setOnClickListener {
            activity?.onBackPressed()
        }
        movieDetailsViewModel.selectedMovieList.observe(
            this.viewLifecycleOwner,
            Observer { movieDetailsViewModel.getMovie() })
        if (savedInstanceState == null) {
            movieDetailsViewModel.setMovie(selectedMovieID)
        }
        movieDetailsViewModel.movieLiveData.observe(this.viewLifecycleOwner, this::viewFill)
    }

    private fun viewFill(movie: Movie) {
        movie.apply {
            binding.background.load(movie.backdrop)
            binding.minimumAge.text = requireContext().getString(
                R.string.pg_rating,
                movie.minimumAge.toString()
            )
            binding.movieTitle.text = movie.title
            binding.genre.text = movie.genres.joinToString { it.name }
            binding.ratingBar.rating = convertRating(movie.ratings)
            binding.totalReviews.text = movie.numberOfRatings.toString()
            binding.storyLine.text = movie.overview

            binding.actorListRecyclerView.apply {
                adapter = MovieDetailsAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            if (movie.actors.isEmpty()) {
                view?.findViewById<TextView>(R.id.cast)?.visibility =
                    View.GONE
            }
            (binding.actorListRecyclerView.adapter as MovieDetailsAdapter).updateActors(movie.actors)
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}






