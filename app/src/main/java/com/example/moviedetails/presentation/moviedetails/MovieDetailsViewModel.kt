package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.*
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.getMoviesList
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie> = _mutableMovieLiveData

    private var _loadingMovieList: MutableLiveData<Boolean> = MutableLiveData()
    val loadingMovieList: LiveData<Boolean> = _loadingMovieList

    @ExperimentalSerializationApi
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingMovieList.value = true
            _mutableMovieLiveData.value = repository.getMovieByIdFromDb(movieId)
         /*   val movies = getMoviesList()
            movies.singleOrNull { it.id == movieId }.let {
                _mutableMovieLiveData.value = it
            }*/
            _loadingMovieList.value = false
        }
    }
}

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

}