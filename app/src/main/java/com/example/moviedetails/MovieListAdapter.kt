package com.example.moviedetails


import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.HeaderViewListAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.contentValuesOf

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


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
            is MovieListViewHolder -> holder.bind(Movie(position))
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

data class Movie(
    val promo_card: MaterialCardView,
    val label_image: ImageView,
    val label_background_linear: ImageView,
    val pegi_info: TextView,
    val ic_like: ImageView,
    val text_genre: TextView,
    val first_star_icon: ImageView,
    val second_star_icon: ImageView,
    val third_star_icon: ImageView,
    val fourth_star_icon: ImageView,
    val fifth_star_icon: ImageView,
    val reviews_quantity: TextView,
    val label_name: TextView,
    val duration: TextView
)