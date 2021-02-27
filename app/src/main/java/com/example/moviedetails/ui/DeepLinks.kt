package com.example.moviedetails.ui

import android.net.Uri
import androidx.core.net.toUri

object DeepLinks {

    fun getMovieDetailsDeepLink(movieId: Int): Uri =
        "https://moviedetails.com/movie/$movieId".toUri()
}