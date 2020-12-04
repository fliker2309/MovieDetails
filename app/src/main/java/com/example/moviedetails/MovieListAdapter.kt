package com.example.moviedetails


import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.HeaderViewListAdapter
import androidx.core.content.contentValuesOf

import androidx.recyclerview.widget.RecyclerView


class MovieListAdapter(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER_ID -> HeaderViewHolder(
                inflater.inflate(
                    R.layout.movie_list_header,
                    parent,
                    false
                )
            )
            TYPE_MOVIES_ID -> MovieListViewHolder(
                inflater.inflate(
                    R.layout.view_holder_movie,
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieListViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Companion.TYPE_HEADER_ID
            else -> Companion.TYPE_MOVIES_ID
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    companion object {
        const val TYPE_HEADER_ID = 0
        const val TYPE_MOVIES_ID = 1
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

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.movie_list_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}