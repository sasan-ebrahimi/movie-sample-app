package com.sebrahimi.moviesample.di

import com.sebrahimi.common.DIKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object MainModule{

    @Provides
    @Named(DIKey.BASE_URL)
    fun provideBaseUrl(): String {
        return "https://api.themoviedb.org/"
    }

    @Provides
    @Named(DIKey.BASE_POSTER_IMAGE_URL)
    fun provideBasePosterImageUrl(): String {
        return "https://image.tmdb.org/t/p/w1280/"
    }

    @Provides
    @Named(DIKey.BASE_BACKDROP_IMAGE_URL)
    fun provideBaseBackDropImageUrl(): String {
        return "https://image.tmdb.org/t/p/w1280/"
    }



}