package com.sebrahimi.repository

import com.sebrahimi.restapi.ResponseWrapper
import com.sebrahimi.restapi.api.movie.MovieApi
import com.sebrahimi.restapi.dto.ApiResponseDTO
import com.sebrahimi.restapi.dto.MovieDTO
import com.sebrahimi.restapi.dto.MovieDetailDTO
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    suspend fun getLatestMovies(page: Int = 1): ResponseWrapper<ApiResponseDTO<List<MovieDTO>>> =movieApi.getLatestMovies(page)

    suspend fun getMovieDetails(id: Long):ResponseWrapper<MovieDetailDTO> = movieApi.getMovieDetails(id)
}