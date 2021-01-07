package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.MovieInteractor
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModel
import com.example.moviedetails.presentation.moviedetails.MovieDetailsViewModelFactory
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMoviesDetailsBinding
import com.example.moviedetails.ui.moviedetails.adapter.ActorAdapter

class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieInteractor(requireContext()))
    }

    private var selectedMovieID: Int = 0
    private lateinit var actorListRecycler: RecyclerView //важно не забыть! добавив это, стал появляться список актеров

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
        _binding = FragmentMoviesDetailsBinding.bind(view)
        binding.backToMainButton.setOnClickListener {
            activity?.onBackPressed()
        }
        viewFill(
            Movie(
                id = 0,
                title = "",
                overview = "",
                poster = "",
                backdrop = "",
                ratings = 0f,
                numberOfRatings = 0,
                minimumAge = 0,
                runtime = 0,
                genres = listOf(),
                actors = listOf()
            )
        )
        movieDetailsViewModel.selectedMovieList.observe(
            this.viewLifecycleOwner,
            { movieDetailsViewModel.getMovie() })
        if (savedInstanceState == null) {
            movieDetailsViewModel.setMovie(selectedMovieID)
        }
        movieDetailsViewModel.movie.observe(this.viewLifecycleOwner, this::viewFill)
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
            binding.totalReviews.text =
                getString(R.string.total_reviews, movie.numberOfRatings.toString())
            binding.storyLine.text = movie.overview
            actorListRecycler = binding.actorListRecyclerView
            val actorAdapter = ActorAdapter()

            val linearLayoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            actorListRecycler.layoutManager = linearLayoutManager
            actorListRecycler.adapter = actorAdapter
            (actorListRecycler.adapter as ActorAdapter).updateActors(actors)
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(movieID: Int): MovieDetailsFragment {
            val movieFragment = MovieDetailsFragment()
            movieFragment.selectedMovieID = movieID
            return movieFragment
        }
    }
}






