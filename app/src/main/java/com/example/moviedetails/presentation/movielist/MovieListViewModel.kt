package com.example.moviedetails.presentation.movielist

import android.app.Application
import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.repository.MoviesRepository
import com.example.moviedetails.data.repository.getMoviesList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
     /*   get() = _mutableMovieListLiveData*/

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val repository: MoviesRepository

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MoviesRepository(movieDao)
        movieListLiveData = repository.readAllMoviesFromDb
    }

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            _mutableMovieListLiveData.value = getMoviesList()
            _loadingLiveData.value = false
        }
    }
}

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

}