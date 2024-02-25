package com.example.moviesapp.presentation.di

import android.app.Application
import com.example.moviesapp.data.retrofit.retrofitdatasource.RetrofitDataSource
import com.example.moviesapp.data.room.roomdatasource.RoomDataSource
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Provides
    @Singleton
    fun providesRepository(roomDataSource: RoomDataSource, retrofitDataSource: RetrofitDataSource): MovieRepository{
        return MovieRepository(roomDataSource, retrofitDataSource)
    }

    @Provides
    @Singleton
    fun providesRoomDataSource(app: Application): RoomDataSource{
        return RoomDataSource(app)
    }

    @Provides
    @Singleton
    fun providesRetrofitDataSource(): RetrofitDataSource{
        return RetrofitDataSource()
    }
}
