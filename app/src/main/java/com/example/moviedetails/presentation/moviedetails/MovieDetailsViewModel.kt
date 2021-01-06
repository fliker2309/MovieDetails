package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.MovieInteractor
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private var _mutableMovie: MutableLiveData<Movie> = MutableLiveData(
        Movie(
            id = 0,
            title = "",
            overview = "",
            poster = "",
            backdrop = "",
            ratings = 0f,
            numberOfRatings = 0,
            minimumAge = 0,
            runtime = 0,
            genres = listOf(),
            actors = listOf()
        )
    )
    private var _selectedMovieList: MutableLiveData<Int> = MutableLiveData(0)

    val movie: LiveData<Movie> get() = _mutableMovie
    val selectedMovieList: LiveData<Int> get() = _selectedMovieList

    //получение фильма с помощью корутин в architecture components
    fun getMovie() {
        viewModelScope.launch {
            val movieID = selectedMovieList.value
            val movies = interactor.getMoviesList()
            val movie = movies.find { actor -> movieID == actor.id }
            if (movie != null) {
                _mutableMovie.postValue(movie)
            }
        }
    }

    fun setMovie(movieID: Int) {
        viewModelScope.launch {
            _selectedMovieList.postValue(movieID)
        }
    }
}