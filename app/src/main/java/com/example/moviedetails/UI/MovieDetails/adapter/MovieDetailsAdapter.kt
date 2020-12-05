package com.example.moviedetails.ui.moviedetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.R
import com.example.moviedetails.data.Actor

class MovieDetailsAdapter(
    val actors: List<Actor>
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

class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // private val idActor: Int = view.findViewById<>()
    private val avatarActor: ImageView = view.findViewById(R.id.first_actor_image)
    private val nameActor : TextView = view.findViewById(R.id.first_actor_name)


    fun bind(actor: Actor) {
        actor.avatar?.let { avatarActor.setImageResource(it) }
        nameActor.text = actor.fullName
    }
}