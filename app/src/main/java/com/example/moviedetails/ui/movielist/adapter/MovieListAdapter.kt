package com.example.moviedetails.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.ui.R

class MovieListAdapter(
    // куда девать movies...
    private val movies: List<Movie>,
    private val cardListener: (Int) -> Unit,
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)

        return MovieListViewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}

class MovieListViewHolder(view: View, private val cardListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val posterImage: ImageView = view.findViewById(R.id.poster)
    private val likeIcon: ImageView = view.findViewById(R.id.ic_like)
    private val movieTitle: TextView = view.findViewById(R.id.movie_title)
    private val minimumAge: TextView = view.findViewById(R.id.minimum_age)
    private val durationText: TextView = view.findViewById((R.id.runtime))
    private val tagsText: TextView = view.findViewById(R.id.genre)
    private val totalReviewText: TextView = view.findViewById(R.id.total_reviews)

    //private val ratingBar: RatingBar = view.findViewById(R.id.poster_ratingBar)
    fun bind(movie: Movie) {
        itemView.setOnClickListener {
            cardListener.invoke(movie.id)
        }
        //загрузка постера фильма
        Glide
            .with(itemView.context)
            .load(movie.poster)
            .centerCrop()
            .placeholder(R.drawable.ic_image_download)
            .error(R.drawable.ic_image_download)
            .into(posterImage)
//сделать дробное оппеделение рейтинга
        likeIcon.setImageResource(R.drawable.ic_like)
        minimumAge.text = itemView.context.getString(
            R.string.pg_rating,
            movie.minimumAge.toString()
        )
        // ratingBar.setCurrentRating(movie.ratings / 2)
        movieTitle.text = movie.title
        durationText.text = itemView.resources.getString(
            R.string.movie_duration,
            movie.runtime.toString()
        )
        tagsText.text = movie.genres.joinToString { it.name }
        totalReviewText.text = itemView.context.getString(
            R.string.total_reviews,
            movie.numberOfRatings.toString()
        )
    }
}




