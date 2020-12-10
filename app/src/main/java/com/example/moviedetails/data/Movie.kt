package com.example.moviedetails.data

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    @DrawableRes
    val label_image: Int,
    @DrawableRes
    val movie_background_image: Int,
    @DrawableRes
    val movie_background_gradient: Int,
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
    val rating_bar: Int,
    val story_line: String,
    val reviews_quantity: String,
    val cast: List<Actor>,
    val duration: String,
    val movie_name: String
)