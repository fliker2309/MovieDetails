package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedetails.domain.MovieInteractor

class MovieListViewModelFactory(
    private val interactor: MovieInteractor
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieListViewModel::class.java -> MovieListViewModel(interactor)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}