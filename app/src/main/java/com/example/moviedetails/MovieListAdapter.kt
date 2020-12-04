package com.example.moviedetails


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView


class MovieListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Companion.TYPE_HEADER
            else -> Companion.TYPE_MOVIES
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_MOVIES = 1
    }
}

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun from(parent: ViewGroup): MovieListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
            return MovieListViewHolder(view)
        }
    }

}