package com.example.moviedetails.ui.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviedetails.data.model.Actor
import com.example.moviedetails.ui.R
import com.example.moviedetails.ui.databinding.ViewHolderActorBinding

class ActorAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun updateActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

class ActorViewHolder private constructor(private val binding: ViewHolderActorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ActorViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderActorBinding.inflate(inflater, parent, false)
            return ActorViewHolder(binding)
        }
    }

    fun bind(actor: Actor) {
        binding.apply {
            actorImage.load(actor.picture) {
                placeholder(R.drawable.ic_image_download)
                error(R.drawable.ic_person)
            }
            actorName.text = actor.name
        }
    }
}
