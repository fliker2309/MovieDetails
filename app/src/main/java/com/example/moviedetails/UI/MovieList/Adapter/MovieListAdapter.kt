package com.example.moviedetails.ui.movielist.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.R

class MovieListAdapter(
    private val movies: List<Movie>,
    private val cardListener: (Long) -> Unit,
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

class MovieListViewHolder(view: View, private val cardListener: (Long) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val labelImage: ImageView = view.findViewById(R.id.label_avengers)
    private val movieName: TextView = view.findViewById(R.id.label_avengers_movie)
    private val pegiInfo: TextView = view.findViewById(R.id.pegi_info)
    private val genre: TextView = view.findViewById(R.id.text_genre)
    private val reviews: TextView = view.findViewById(R.id.reviews_quantity)
    private val duration: TextView = view.findViewById((R.id.avengers_movie_duration))
    private val likeIcon: ImageView = view.findViewById(R.id.ic_like)
    private val fifthStar: ImageView = view.findViewById(R.id.fifth_star_icon)

    fun bind(movie: Movie) {
        labelImage.setImageResource(movie.label_image)
        movieName.text = movie.movie_name
        pegiInfo.text = movie.pegi_info
        genre.text = movie.text_genre
        reviews.text = movie.reviews_quantity
        duration.text = movie.duration
        likeIcon.setImageResource(movie.ic_like)
        fifthStar.setImageResource((movie.fifth_star_icon))
        itemView.setOnClickListener {
            cardListener.invoke(movie.id)
        }

    }

}



