package com.example.moviedetails.ui.moviedetails

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.moviedetails.adapter.MovieDetailsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_ID_KEY to movieId)
        }
    }

    private var movieId: Int? = null
    private lateinit var actorListRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(MOVIE_ID_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //инициализация movie
        var movie: Movie?
        actorListRecycler = view.findViewById(R.id.actor_list_recycler_view)
        val movieDetailsAdapter = MovieDetailsAdapter()

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        actorListRecycler.layoutManager = linearLayoutManager
        actorListRecycler.adapter = movieDetailsAdapter

        val backgroundImage: ImageView = view.findViewById(R.id.background)
        val minimumAge: TextView = view.findViewById(R.id.minimum_age)
        val movieTitle: TextView = view.findViewById(R.id.movie_title)
        val genre: TextView = view.findViewById(R.id.genre)
        val rating: RatingBar = view.findViewById(R.id.ratingBar)
        val totalReviews: TextView = view.findViewById(R.id.total_reviews)
        val storyLine: TextView = view.findViewById(R.id.overview)

        view.findViewById<Button>(R.id.back_to_main_button)?.setOnClickListener {
            activity?.onBackPressed()
        }
        CoroutineScope(Dispatchers.IO).launch {
            movie = loadMovies(requireContext()).findLast { it.id == movieId }
            withContext(Dispatchers.Main) {
                movie?.let {
                    Glide
                        .with(this@MovieDetailsFragment)// для контекста фрагмента, а не корутины
                        .load(movie?.backdrop)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_download)
                        .error(R.drawable.ic_image_download)
                        .into(backgroundImage)

                    minimumAge.text = context!!.getString(
                        R.string.pg_rating,
                        movie?.minimumAge.toString()
                    )
                    movieTitle.text = movie?.title
                    genre.text = movie?.genres?.joinToString { it.name }
                    rating.rating = convertRating(it.ratings)
                    totalReviews.text = movie?.numberOfRatings.toString()
                    storyLine.text = movie?.overview
                    (actorListRecycler.adapter as MovieDetailsAdapter).updateActors(it.actors)
                }
            }
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f
}



