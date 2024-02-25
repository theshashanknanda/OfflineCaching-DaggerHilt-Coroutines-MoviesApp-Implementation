package com.example.moviesapp.data.room.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.data.room.dao.MovieDao
import com.example.moviesapp.domain.entities.Movie

@Database(entities = [Movie::class], exportSchema = false, version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun dao(): MovieDao

    companion object{
        var LOCK = Any()
        var MOVIE_DB: MovieDatabase? = null

        fun getInstance(app: Application): MovieDatabase?{
            synchronized(LOCK){
                return MOVIE_DB ?: createNewInstance(app)
            }
        }

        fun createNewInstance(app: Application): MovieDatabase?{
            MOVIE_DB = Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db").build()

            return MOVIE_DB;
        }
    }
}
