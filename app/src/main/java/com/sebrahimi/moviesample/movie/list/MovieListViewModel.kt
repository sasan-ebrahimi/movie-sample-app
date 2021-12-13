package com.sebrahimi.moviesample.movie.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sebrahimi.repository.MovieRepository
import com.sebrahimi.repository.MovieSource
import com.sebrahimi.restapi.dto.MovieDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val movies: Flow<PagingData<MovieDTO>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(movieRepository)
    }.flow

}