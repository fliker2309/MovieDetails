package com.example.moviedetails.ui.moviedetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moviedetails.ui.R
import com.example.moviedetails.data.Actor

class MovieDetailsAdapter(
    private var actors: List<Actor>
) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}

class ActorViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
    private val avatar: ImageView = listItemView.findViewById(R.id.actor_image)
    private val name: TextView = listItemView.findViewById(R.id.actor_name)
    private val centerCropScaleType = CenterCrop()
    private val roundedCorners = RoundedCorners(R.dimen.actor_corner_radius)

    fun bind(actor: Actor) {
        Glide.with(itemView.context)
            .load(actor.picture)
            .transform(centerCropScaleType, roundedCorners)
            .placeholder(R.drawable.ic_image_download)
            .error(R.drawable.ic_image_download)
            .into(avatar)

        name.text = actor.name
    }
}
