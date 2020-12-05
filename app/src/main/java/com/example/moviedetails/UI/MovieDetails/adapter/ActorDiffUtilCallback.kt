package com.example.moviedetails

import androidx.recyclerview.widget.DiffUtil


class ActorDiffUtilCallback : DiffUtil.ItemCallback<Actor>() {

    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
        oldItem == newItem
}