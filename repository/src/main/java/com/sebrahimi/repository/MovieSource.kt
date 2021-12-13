package com.sebrahimi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sebrahimi.restapi.ResponseWrapper
import com.sebrahimi.restapi.dto.MovieDTO
import javax.inject.Inject

class MovieSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int, MovieDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        return try {
            val nextPage = params.key ?: 1
            when (val movieListResponse = movieRepository.getLatestMovies(nextPage)) {
                is ResponseWrapper.Success -> {
                    LoadResult.Page(
                        data = movieListResponse.data.results,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = movieListResponse.data.page +1
                    )
                }
                is ResponseWrapper.Failure -> {
                    LoadResult.Error(movieListResponse.error)
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        return null
    }
}