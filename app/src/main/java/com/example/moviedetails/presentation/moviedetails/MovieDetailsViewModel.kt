package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.network.TheMovieDbApi
import com.example.moviedetails.domain.network.getMoviesList

import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MovieDetailsViewModel : ViewModel() {

    private var _mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    private var _loadingMovieList: MutableLiveData<Boolean> = MutableLiveData()

    @ExperimentalSerializationApi
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingMovieList.value = true
            val movies = getMoviesList()
            movies.singleOrNull { it.id == movieId }?.let {
                _mutableMovieLiveData.value = it
            }
            _loadingMovieList.value = false
        }
    }


}