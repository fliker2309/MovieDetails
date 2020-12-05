package com.example.moviedetails.data

import androidx.annotation.DrawableRes

data class Actor(
    val id: Int,
    val fullName: String,
    @DrawableRes
    val avatar: Int? = null
)