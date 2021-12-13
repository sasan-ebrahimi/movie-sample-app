package com.sebrahimi.moviesample.movie.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebrahimi.repository.MovieRepository
import com.sebrahimi.restapi.ResponseWrapper
import com.sebrahimi.restapi.dto.ApiResponseDTO
import com.sebrahimi.restapi.dto.MovieDetailDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _movieDetails : MutableState<MovieDetailDTO?> = mutableStateOf(null)
    val movieDetails : State<MovieDetailDTO?> = _movieDetails

    fun fetchMovieDetails(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                when(val result =movieRepository.getMovieDetails(id) ){
                    is ResponseWrapper.Success -> {
                        _movieDetails.value = result.data
                    }
                    is ResponseWrapper.Failure -> {
                        // TODO
                    }
                }
            }
        }
    }
}