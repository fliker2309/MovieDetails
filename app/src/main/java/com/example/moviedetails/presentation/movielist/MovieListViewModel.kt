package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedetails.data.db.MovieRepository
import com.example.moviedetails.data.db.entity.Movie
import com.example.moviedetails.data.network.getMoviesList
/*import com.example.moviedetails.data.network.fetchMovies*/
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@InternalCoroutinesApi
class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _mutableMovieListLiveData

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    init {
        viewModelScope.launch {
            _mutableMovieListLiveData.value = repository.readAllMoviesFromDb()
        }
    }

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            val loadedMovies = getMoviesList()
            _mutableMovieListLiveData.value = loadedMovies
            repository.insertMoviesInDb(loadedMovies)
        }
    }

   /* fun fetchedMovies() {
        swipeRefreshLayout.isRefreshing = true
        val fetchMovies = fetchMovies()
        _mutableMovieListLiveData.value = fetchMovies
        swipeRefreshLayout.isRefreshing = false
    }*/


}

