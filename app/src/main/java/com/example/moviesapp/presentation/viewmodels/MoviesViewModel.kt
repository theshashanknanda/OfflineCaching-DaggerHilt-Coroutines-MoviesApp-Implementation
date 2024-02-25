package com.example.moviesapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.domain.entities.Movie
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {
    var movies: LiveData<List<Movie>> = repository.movies!!

    suspend fun getPopularMovies(){
        repository.fetchMovies()
    }
}
