package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModel
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

    val movieDetailsViewModel: MovieDetailsViewModel
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
        val movieId = arguments?.getInt(MOVIE_ID_KEY)

        movieDetailsViewModel.getMovie(movieId!!)

        binding.backToMainButton.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.actorListRecyclerView.apply {
            adapter = MovieDetailsAdapter()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        movieDetailsViewModel.movieLiveData.observe(viewLifecycleOwner) { movie: Movie ->
            binding.background.load(movie.backdrop)
            binding.minimumAge.text = context!!.getString(
                R.string.pg_rating,
                movie.minimumAge.toString()
            )
            binding.movieTitle.text = movie.title
            binding.genre.text = movie.genres.joinToString { it.name }
            binding.ratingBar.rating = convertRating(movie.ratings)
            binding.totalReviews.text = movie.numberOfRatings.toString()
            binding.storyLine.text = movie.overview
            if (movie.actors.isEmpty()) view.findViewById<TextView>(R.id.cast).visibility =
                View.GONE
            (binding.actorListRecyclerView.adapter as MovieDetailsAdapter).updateActors(movie.actors)
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



