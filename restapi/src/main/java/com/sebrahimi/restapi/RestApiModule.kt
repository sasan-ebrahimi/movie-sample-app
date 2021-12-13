package com.sebrahimi.restapi

import com.sebrahimi.restapi.api.movie.MovieApi
import com.sebrahimi.restapi.api.movie.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {

    @Provides
    fun provideMovieService(
        retrofit: Retrofit
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    fun provideMovieApi(
        movieService: MovieService
    ): MovieApi {
        return MovieApi(movieService)
    }
}