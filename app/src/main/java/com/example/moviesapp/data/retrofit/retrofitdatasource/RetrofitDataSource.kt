package com.example.moviesapp.data.retrofit.retrofitdatasource

import com.example.moviesapp.data.retrofit.api.MovieApiService
import com.example.moviesapp.data.retrofit.retrofitinstance.RetrofitInstance
import com.example.moviesapp.domain.entities.MoviesResponse
import retrofit2.Response

class RetrofitDataSource {
    private val retrofit = RetrofitInstance.getInstance()
    private val api = retrofit?.create(MovieApiService::class.java)

    suspend fun getMoviesResponse(language: String, page: String, apiKey: String): Response<MoviesResponse>?{
        return api?.getPopularMovies(language, page, apiKey)
    }
}
