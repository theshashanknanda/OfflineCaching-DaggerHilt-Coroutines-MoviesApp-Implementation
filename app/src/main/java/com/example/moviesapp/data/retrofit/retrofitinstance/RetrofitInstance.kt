package com.example.moviesapp.data.retrofit.retrofitinstance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance{
    companion object{
        private var LOCK = Any();
        private var INSTANCE: Retrofit? = null
        private var BASE_URL = "https://api.themoviedb.org/3/movie/popular/"

        fun getInstance(): Retrofit?{
            synchronized(LOCK){
                return INSTANCE ?: createNewInstance()
            }
        }

        private fun createNewInstance(): Retrofit?{
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return INSTANCE
        }
    }
}
