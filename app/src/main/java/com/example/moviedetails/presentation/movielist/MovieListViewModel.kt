package com.example.moviedetails.presentation.movielist

import android.app.Application
import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.db.MovieDatabase
import com.example.moviedetails.data.db.entity.MovieEntity
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.repository.getMoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val repository: MovieRepository

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        movieListLiveData = repository.readAllMoviesFromDb
    }

   fun insertMoviesInDb(movieEntity: MovieEntity) {
       viewModelScope.launch(Dispatchers.IO) {

           repository.insertMoviesInDb(movieEntity)
       }
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