package com.example.moviedetails


import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView


class MovieListAdapter(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerView.ViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}