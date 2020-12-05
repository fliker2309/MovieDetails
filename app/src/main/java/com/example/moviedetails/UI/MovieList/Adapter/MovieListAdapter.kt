package com.example.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import com.example.moviedetails.data.Movie
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class MovieListAdapter(
    context: Context,
    private val movies: List<Movie>,
    private val cardListener: (Long) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater: LayoutInflater = from(context)


    fun getMovieCount(): Int = movies.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MovieListViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(movie: Movie, cardListener: (Long) -> Unit) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}


class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // creating variables


    fun bind(movie: Movie, cardListener: (Long) -> Unit) {

    }


    //  companion object {
    //    fun from(parent: ViewGroup): MovieListViewHolder {
    //      val inflater = from(parent.context)
    //    val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
    //  return MovieListViewHolder(view)
    // }
    //  }
}

sealed class DataItem {
    data class MovieItem(val movie: Movie) : DataItem() {
        override val id: Long
            get() = movie.id

    }


    abstract val id: Long
}


