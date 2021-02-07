package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.*
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.db.entity.Movie
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie> = _mutableMovieLiveData

    @ExperimentalSerializationApi
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _mutableMovieLiveData.value = repository.getMovieByIdFromDb(movieId)
        }
    }
}
