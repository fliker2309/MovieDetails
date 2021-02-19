package com.example.moviedetails.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviedetails.data.db.entity.Movie
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.ViewHolderMovieBinding

class MovieListAdapter(
    private var movies: List<Movie>,
    private val cardListener: (Int) -> Unit
) : ListAdapter<DataItem, RecyclerView.ViewHolder>(MovieDiffUtilCallback()) {

    companion object {
        const val MOVIE_ITEM_ID = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MOVIE_ITEM_ID -> MovieListViewHolder.from(parent, cardListener)
            else -> throw NoSuchElementException("Adapter doesn't know this view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is MovieListViewHolder -> holder.bind(
                (item as DataItem.MovieItem).movie,
                cardListener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.MovieItem -> MOVIE_ITEM_ID
        }
    }

    fun setMovies(movies: List<Movie>) {
        val resultList = movies.map { DataItem.MovieItem(it) }
        submitList(resultList)
    }
}




@GlideModule
class MovieListViewHolder private constructor(
    private val binding: ViewHolderMovieBinding, private val cardListener: (Int) -> Unit
) :
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

    fun bind(movie: Movie, cardListener: (Int) -> Unit) {
        this.movieId = movie.id
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

sealed class DataItem {
    data class MovieItem(val movie: Movie) : DataItem() {
        override val id: Int
            get() = movie.id
    }

    abstract val id: Int
}

