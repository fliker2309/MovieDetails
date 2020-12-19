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
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.moviedetails.adapter.MovieDetailsAdapter

private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

class MovieDetailsFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        val backgroundImage: ImageView = view.findViewById(R.id.background)
        val minimumAge: TextView = view.findViewById(R.id.minimum_age)
        val movieTitle: TextView = view.findViewById(R.id.movie_title)
        val genre: TextView = view.findViewById(R.id.genre)
        val rating: RatingBar = view.findViewById(R.id.ratingBar)
        val reviews: TextView = view.findViewById(R.id.total_reviews)
        val storyLine: TextView = view.findViewById(R.id.overview)

      !!  val cast = movie?.cast
        val movieDetailsAdapter = cast?.let {
            MovieDetailsAdapter(
                actors = cast
            )
        }
        !!movie?.let {
            backgroundImage.setImageResource(movie.movie_background_image)
            pegiInfo.text = movie.pegi_info
            movieName.text = movie.movie_name
            genre.text = movie.text_genre
            rating.numStars = movie.rating_bar
            reviews.text = movie.reviews_quantity
            storyLine.text = movie.overview
            actorListRecycler = view.findViewById(R.id.actor_list_recycler_view)
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            actorListRecycler.layoutManager = linearLayoutManager
            actorListRecycler.adapter = movieDetailsAdapter
        }
        view.findViewById<Button>(R.id.back_to_main_button)?.setOnClickListener {
            activity?.onBackPressed()
        }
        return view
    }

    companion object {
        fun newInstance(movieId: Long): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = bundleOf(
                    MOVIE_ID_KEY to movieId
                )
            }
        }
    }
}
