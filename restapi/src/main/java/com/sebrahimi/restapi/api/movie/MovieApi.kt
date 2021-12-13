package com.sebrahimi.restapi.api.movie

import com.sebrahimi.restapi.ResponseWrapper
import com.sebrahimi.restapi.dto.ApiResponseDTO
import com.sebrahimi.restapi.dto.MovieDTO
import com.sebrahimi.restapi.dto.MovieDetailDTO
import javax.inject.Inject

class MovieApi @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getLatestMovies(page: Int = 1): ResponseWrapper<ApiResponseDTO<List<MovieDTO>>> {
        return ResponseWrapper.processRetrofitResponsePaged(movieService.getUpcomingMovies(page))
    }

    suspend fun getMovieDetails(id: Long): ResponseWrapper<MovieDetailDTO> {
        return ResponseWrapper.processRetrofitResponse(movieService.getMovieDetails(id))
    }
}