package com.example.moviedetails.presentation.moviedetails

import androidx.lifecycle.*
import com.example.moviedetails.data.Movie
import com.example.moviedetails.domain.MovieInteractor
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private var _mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData(
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
    private var _mutableSelectedMovieList: MutableLiveData<Int> = MutableLiveData(0)

    val movieLiveData: LiveData<Movie> get() = _mutableMovieLiveData
    val selectedMovieList: LiveData<Int> get() = _mutableSelectedMovieList

    //получение фильма с помощью корутин в architecture components
    fun getMovie() {
        viewModelScope.launch {
            val movieID = selectedMovieList.value
            val movies = interactor.getMoviesList()
            val movie = movies.find { actor -> movieID == actor.id }
            if (movie != null) {
                _mutableMovieLiveData.setValue(movie)
            }
        }
    }

    fun setMovie(movieID: Int) {
        viewModelScope.launch {
            _mutableSelectedMovieList.setValue(movieID)
        }
    }
}