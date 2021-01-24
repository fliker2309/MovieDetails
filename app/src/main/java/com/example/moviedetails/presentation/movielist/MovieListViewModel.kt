package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.*
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.getMoviesList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _mutableMovieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        viewModelScope.launch {
            _mutableMovieListLiveData.value = repository.readAllMoviesFromDb()
        }
    }

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            val loadedMovies = getMoviesList()
            _mutableMovieListLiveData.value = loadedMovies
            _loadingLiveData.value = false
            repository.insertMoviesInDb(loadedMovies)
        }
    }
}

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

}