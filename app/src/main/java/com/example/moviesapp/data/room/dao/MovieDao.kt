package com.example.moviesapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesapp.domain.entities.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>
}
