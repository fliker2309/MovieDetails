package com.example.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder



class MovieListAdapter(

    private val cardListener: (Long) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater: LayoutInflater = from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(movie : Movie,cardListener:(Long)-> Unit ) {
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
fun generateActors(): List<Actor>{
    return listOf  (

            )

}

data class Movie(
    val id : Long,
    @DrawableRes
    val label_image: Int,
    @DrawableRes
    val label_background_linear: Int,
    val pegi_info: String,
    @DrawableRes
    val ic_like: Int,
    val text_genre: String,
    @DrawableRes
    val first_star_icon: Int,
    @DrawableRes
    val second_star_icon: Int,
    @DrawableRes
    val third_star_icon: Int,
    @DrawableRes
    val fourth_star_icon: Int,
    @DrawableRes
    val fifth_star_icon: Int,
    val reviews_quantity: String,
    val cast : List<Actor>,
    val label_name: String,

    val duration: Int
)
