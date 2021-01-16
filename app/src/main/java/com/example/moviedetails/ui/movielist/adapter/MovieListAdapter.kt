package com.example.moviedetails.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.FragmentMovieListBinding
import com.example.moviedetails.ui.databinding.ViewHolderMovieBinding

class MovieListAdapter(
    private var movies: List<Movie>,
    private val cardListener: (Int) -> Unit
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieListViewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size
}
@GlideModule
class MovieListViewHolder(view: View, private val cardListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val posterImage: ImageView = view.findViewById(R.id.poster)
    private val likeIcon: ImageView = view.findViewById(R.id.ic_like)
    private val movieTitle: TextView = view.findViewById(R.id.movie_title)
    private val minimumAge: TextView = view.findViewById(R.id.minimum_age)
    private val genreText: TextView = view.findViewById(R.id.genre)
    private val totalReviewText: TextView = view.findViewById(R.id.total_reviews)
    private val rating: RatingBar = view.findViewById(R.id.ratingBar)

    fun bind(movie: Movie) {
        itemView.setOnClickListener {
            cardListener.invoke(movie.id)
        }

        Glide
            .with(itemView.context)
            .load(movie.poster)
            .centerCrop()
            .placeholder(R.drawable.ic_image_download)
            .error(R.drawable.ic_image_download)
            .into(posterImage)

        likeIcon.setImageResource(R.drawable.ic_like)
        minimumAge.text = itemView.context.getString(
            R.string.pg_rating,
            movie.minimumAge.toString()
        )
        rating.rating = convertRating(movie.ratings)
        movieTitle.text = movie.title
        genreText.text = movie.genres.joinToString { it.name }
        totalReviewText.text = itemView.context.getString(
            R.string.total_reviews,
            movie.numberOfRatings.toString()
        )
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f
}




