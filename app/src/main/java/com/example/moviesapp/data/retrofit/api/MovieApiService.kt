package com.example.moviesapp.data.retrofit.api

import com.example.moviesapp.domain.entities.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("api_key") apiKey: String
    ): Response<MoviesResponse>
}
