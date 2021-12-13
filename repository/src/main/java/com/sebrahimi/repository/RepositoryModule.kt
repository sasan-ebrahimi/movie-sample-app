package com.sebrahimi.repository

import com.sebrahimi.restapi.api.movie.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository {
        return MovieRepository(movieApi)
    }

}