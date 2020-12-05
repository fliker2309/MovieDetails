package com.example.moviedetails

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedetails.data.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}