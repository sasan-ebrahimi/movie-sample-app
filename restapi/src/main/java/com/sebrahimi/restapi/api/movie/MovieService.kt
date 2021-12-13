package com.sebrahimi.restapi.api.movie

import com.sebrahimi.restapi.dto.ApiResponseDTO
import com.sebrahimi.restapi.dto.MovieDTO
import com.sebrahimi.restapi.dto.MovieDetailDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path


interface MovieService {
    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page:Int): Response<ApiResponseDTO<List<MovieDTO>>>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetails(@Path("id") id:Long): Response<MovieDetailDTO>

}