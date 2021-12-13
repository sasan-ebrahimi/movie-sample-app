package com.sebrahimi.moviesample.movie.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.sebrahimi.common.DIKey
import com.sebrahimi.moviesample.ui.theme.MovieSampleTheme
import com.sebrahimi.restapi.dto.MovieDTO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {

    @Inject
    @Named(DIKey.BASE_POSTER_IMAGE_URL)
    lateinit var imageBaseUrl: String

    companion object{

        private const val MOVIE = "MOVIE"

        fun start(context: Context, movie: MovieDTO){
            val intent = Intent(context, MovieDetailsActivity::class.java);
            intent.putExtra(MOVIE, movie);
            context.startActivity(intent);
        }
    }

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = intent.getParcelableExtra<MovieDTO?>(MOVIE)
        movie?.let {
            viewModel.fetchMovieDetails(it.id)

            setContent {
                MovieSampleTheme {
                    Surface(
                        modifier = Modifier.wrapContentSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        MovieDetailsUI(imageBaseUrl,it,viewModel)
                    }
                }
            }
        } ?: run {
            Toast.makeText(this, "INVALID MOVIE INFO" , Toast.LENGTH_LONG).show()
            finish()
        }


    }
}

