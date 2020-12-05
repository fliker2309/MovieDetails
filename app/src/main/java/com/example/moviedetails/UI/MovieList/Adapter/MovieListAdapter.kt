package com.example.moviedetails


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView

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
//

    fun bind(movie: Movie) {
        labelImage.setImageResource(movie.label_image)
        movieName.text = movie.movie_name
        itemView.setOnClickListener {
            cardListener.invoke(movie.id)
        }

    }


}



