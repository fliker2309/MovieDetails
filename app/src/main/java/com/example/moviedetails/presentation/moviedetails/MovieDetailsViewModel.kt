package com.example.moviedetails.presentation.moviedetails

import android.app.Application
import android.app.VoiceInteractor
import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.data.loadMovies
import com.example.moviedetails.domain.MovieInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private var _movieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = _movieLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) { loadMovies(app.applicationContext) }
            movies.singleOrNull { it.id == movieId }?.let {
                _movieLiveData.value = it
            }
        }
    }
}