package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.MovieInteractor
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieListLiveData

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
          viewModelScope.launch {
              val movies = interactor.getMoviesList()
              _mutableMovieListLiveData.setValue(interactor.getMoviesList())
          }
        }
    }
}