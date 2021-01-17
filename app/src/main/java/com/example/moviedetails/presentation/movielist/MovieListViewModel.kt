package com.example.moviedetails.presentation.movielist

import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.repository.getMoviesList
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MovieListViewModel : ViewModel() {

    private var _mutableMovieListLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _mutableMovieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            _mutableMovieListLiveData.value = getMoviesList()
            _loadingLiveData.value = false
        }
    }
}