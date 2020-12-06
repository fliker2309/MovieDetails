package com.example.moviedetails.ui.moviedetails.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedetails.data.Actor


class ActorDiffUtilCallback : DiffUtil.ItemCallback<Actor>() {

    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
        oldItem == newItem
}