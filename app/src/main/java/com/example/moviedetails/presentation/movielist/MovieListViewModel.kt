package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.*
import com.example.moviedetails.data.db.MovieLocalDataSource
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.getMoviesList
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieListViewModel(
    private val localDataSource: MovieLocalDataSource
) :
    ViewModel() {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _mutableMovieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        viewModelScope.launch {
            _mutableMovieListLiveData.value = localDataSource.readAllMoviesFromDb()
        }
    }

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            val loadedMovies = getMoviesList()
            _mutableMovieListLiveData.value = loadedMovies
            _loadingLiveData.value = false
            localDataSource.insertMoviesInDb(loadedMovies)
        }
    }
}

