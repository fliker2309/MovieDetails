package com.example.moviedetails.ui.moviedetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviedetails.data.Actor
import com.example.moviedetails.ui.R

class ActorAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = listOf()

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

    fun bind(actor: Actor) {
        avatar.load(actor.picture)
        name.text = actor.name
    }
}
