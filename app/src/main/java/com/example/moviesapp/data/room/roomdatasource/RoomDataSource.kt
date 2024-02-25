package com.example.moviesapp.data.room.roomdatasource

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviesapp.data.room.database.MovieDatabase
import com.example.moviesapp.domain.entities.Movie

class RoomDataSource(app: Application) {
    val room = MovieDatabase.getInstance(app)
    val dao = room?.dao()

    fun getAllMovies(): LiveData<List<Movie>>?{
        return dao?.getAllMovies()
    }

    fun insertAllMovies(movies: List<Movie>){
        dao?.insertAllMovies(movies)
    }

    fun deleteAllMovies(){
        dao?.deleteAllMovies()
    }
}