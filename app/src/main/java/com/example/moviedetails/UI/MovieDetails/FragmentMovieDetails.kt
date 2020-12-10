package com.example.moviedetails.ui.moviedetails

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.R
import com.example.moviedetails.data.Actor
import com.example.moviedetails.data.DataContainer
import com.example.moviedetails.ui.moviedetails.adapter.MovieDetailsAdapter

private const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

class MovieDetailsFragment : Fragment() {

    private var movieId: Long? = null
    private val actors: List<Actor> = listOf()
    private lateinit var actorListRecycler: RecyclerView
    private var fragmentMovieDetailsClickListener: ClickListenerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getLong(MOVIE_ID_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        val movie = movieId?.let { DataContainer.getMovie(it) }
        val backgroundImage: ImageView = view.findViewById(R.id.background)
        val pegiInfo: TextView = view!!.findViewById(R.id.pegi_info)
        val movieName: TextView = view.findViewById(R.id.film_name)
        val genre: TextView = view.findViewById(R.id.text_genre)
        val rating: RatingBar = view.findViewById(R.id.ratingBar)
        val reviews: TextView = view.findViewById(R.id.reviews)
        val storyLine: TextView = view.findViewById(R.id.story_line_description)

        movie?.let {
            backgroundImage.setImageResource(movie.movie_background_image)
            pegiInfo.text = movie.pegi_info
            movieName.text = movie.movie_name
            genre.text = movie.text_genre
            rating.numStars = movie.rating_bar
            reviews.text = movie.reviews_quantity
            storyLine.text = movie.story_line

        }
        val movieDetailsAdapter = MovieDetailsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        actorListRecycler = view.findViewById(R.id.actor_list_recycler_view)
        actorListRecycler.layoutManager = linearLayoutManager
        actorListRecycler.adapter = movieDetailsAdapter

        view.findViewById<Button>(R.id.back_to_main_button)?.setOnClickListener {
            activity?.onBackPressed()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerFragment) {
            fragmentMovieDetailsClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMovieDetailsClickListener = null
    }

    companion object {
        fun newInstance(movieId: Long) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE_ID_KEY to movieId)
        }
    }
}

interface ClickListenerFragment {
    fun toSecondFragment()
}