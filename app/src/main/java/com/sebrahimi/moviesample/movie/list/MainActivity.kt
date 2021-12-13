package com.sebrahimi.moviesample.movie.list

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sebrahimi.common.DIKey.BASE_BACKDROP_IMAGE_URL
import com.sebrahimi.moviesample.ui.theme.MovieSampleTheme
import com.sebrahimi.restapi.dto.MovieDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named(BASE_BACKDROP_IMAGE_URL)
    lateinit var imageBaseUrl: String
    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieSampleTheme {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieList(movies = viewModel.movies, imageBaseUrl,this)
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: Flow<PagingData<MovieDTO>>,imageBaseUrl: String, context: Context) {
    val lazyMovieItems: LazyPagingItems<MovieDTO> = movies.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyMovieItems) { item ->
            item?.let {
                MovieItem(movie = it,imageBaseUrl, context)
            }
        }
    }
}


