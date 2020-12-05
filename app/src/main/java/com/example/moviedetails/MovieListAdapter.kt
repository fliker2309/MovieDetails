package com.example.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.card.MaterialCardView


class MovieListAdapter(
    context: Context
) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater: LayoutInflater = from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
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