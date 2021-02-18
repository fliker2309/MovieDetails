package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedetails.data.db.MovieLocalDataSource

class MovieDetailsViewModelFactory(private val localDataSource: MovieLocalDataSource) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(localDataSource)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}