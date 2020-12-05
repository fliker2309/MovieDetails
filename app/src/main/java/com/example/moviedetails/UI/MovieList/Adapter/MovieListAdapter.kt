package com.example.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviedetails.data.Movie

class MovieListAdapter(

    private val cardListener: (Long) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(movie : Movie, cardListener:(Long)-> Unit ) {
        binding.apply{
            moviePromoCard.setOnDebouncedClickListener{
                cardListener(movie.id)
            }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}


class MovieListViewHolder(itemView: View) : ViewHolder(itemView) {
    companion object {
        fun from(parent: ViewGroup): MovieListViewHolder {
            val inflater = from(parent.context)
            val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
            return MovieListViewHolder(view)
        }
    }
}



