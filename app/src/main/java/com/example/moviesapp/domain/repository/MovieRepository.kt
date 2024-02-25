package com.example.moviesapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.retrofit.retrofitdatasource.RetrofitDataSource
import com.example.moviesapp.data.room.roomdatasource.RoomDataSource
import com.example.moviesapp.domain.entities.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MovieRepository(private val roomDataSource: RoomDataSource, private val retrofitDataSource: RetrofitDataSource) {

    var movies: LiveData<List<Movie>>? = roomDataSource.getAllMovies()

    suspend fun fetchMovies(){
        var response = retrofitDataSource.getMoviesResponse("en-US", "1", "b041795f188725c443946a5055930ef1")
        if(response != null){
            clearRoom()
            populateMoviesIntoRoom(response.body()?.results);
        }
    }

    private fun clearRoom(){
        roomDataSource.deleteAllMovies()
    }

    private fun populateMoviesIntoRoom(response: List<Movie>?) {
        roomDataSource.insertAllMovies(response!!)
    }

}
