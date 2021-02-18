package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedetails.data.db.MovieLocalDataSource
import kotlinx.coroutines.InternalCoroutinesApi

class MovieListViewModelFactory(private val localDataSource: MovieLocalDataSource) :
    ViewModelProvider.Factory {
    @InternalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieListViewModel::class.java -> MovieListViewModel(localDataSource)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}