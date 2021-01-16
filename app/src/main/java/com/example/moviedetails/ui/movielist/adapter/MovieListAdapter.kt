package com.example.moviedetails.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.ViewHolderMovieBinding

class MovieListAdapter(
    private var movies: List<Movie>,
    private val cardListener: (Int) -> Unit
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.from(parent, cardListener)
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
class MovieListViewHolder private constructor(
    private val binding: ViewHolderMovieBinding, private val cardListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup, cardListener: (Int) -> Unit): MovieListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
               ViewHolderMovieBinding.inflate(inflater, parent, false)
            return MovieListViewHolder(binding, cardListener)
        }
    }
    private var movieId: Int? = null

    init {
        binding.moviePromoCard.setOnClickListener {
            movieId?.let { id ->
                cardListener(id)
            }
        }
    }
    fun bind(movie: Movie) {
        itemView.setOnClickListener {
            cardListener.invoke(movie.id)
        }
        binding.apply {
            Glide
                .with(itemView.context)
                .load(movie.poster)
                .centerCrop()
                .placeholder(R.drawable.ic_image_download)
                .error(R.drawable.ic_image_download)
                .into(poster)
            icLike.setImageResource(R.drawable.ic_like)
            movieTitle.text = movie.title
            minimumAge.text = itemView.context.getString(
                R.string.pg_rating,
                movie.minimumAge.toString()
            )
            ratingBar.rating = convertRating(movie.ratings)
            genre.text = movie.genres.joinToString() { it.name }
            totalReviews.text = itemView.context.getString(
                R.string.total_reviews,
                movie.numberOfRatings.toString()
            )
        }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f
}




