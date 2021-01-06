package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
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
import com.example.moviedetails.ui.moviedetails.adapter.ActorAdapter



class MovieDetailsFragment : Fragment() {


    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieInteractor(requireContext()))
    }

    private var selectedMovieID: Int = 0


    private var binding: FragmentMoviesDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesDetailsBinding.bind(view)
        binding!!.backToMainButton.setOnClickListener {
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
            Observer { movieDetailsViewModel.getMovie() })
        if (savedInstanceState == null) {
            movieDetailsViewModel.setMovie(selectedMovieID)
        }
        movieDetailsViewModel.movieLiveData.observe(this.viewLifecycleOwner, this::viewFill)
    }

    private fun viewFill(movie: Movie) {
        movie.apply {
            binding!!.background.load(movie.backdrop)
            binding!!.minimumAge.text = requireContext().getString(
                R.string.pg_rating,
                movie.minimumAge.toString()
            )
            binding!!.movieTitle.text = movie.title
            binding!!.genre.text = movie.genres.joinToString { it.name }
            binding!!.ratingBar.rating = convertRating(movie.ratings)
            binding!!.totalReviews.text = movie.numberOfRatings.toString()
            binding!!.storyLine.text = movie.overview
            val actorListRecyclerView = binding!!.actorListRecyclerView

            actorListRecyclerView.adapter = ActorAdapter()
            actorListRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            if (actors.isNotEmpty()) {
                actorListRecyclerView.visibility = View.VISIBLE

            } else {
                actorListRecyclerView.visibility = View.INVISIBLE

            }
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    override fun onDestroyView() {
        binding = null
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






