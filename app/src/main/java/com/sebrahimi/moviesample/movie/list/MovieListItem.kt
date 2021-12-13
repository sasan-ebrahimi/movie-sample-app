package com.sebrahimi.moviesample.movie.list

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.sebrahimi.moviesample.movie.details.MovieDetailsActivity
import com.sebrahimi.moviesample.ui.components.RatingView
import com.sebrahimi.moviesample.ui.theme.*
import com.sebrahimi.restapi.dto.MovieDTO

val ratingBarSize = 13.dp

@Composable
fun MovieItem(movie: MovieDTO,imageBaseUrl: String, context: Context) {
    val elementsAroundSpacing = 30.dp
    val itemHeight = 160.dp
    val itemWidth = 120.dp
    Row(
        modifier = Modifier
            .clickable(
                enabled = true,
                onClickLabel = "Clickable image",
                onClick = {
                    MovieDetailsActivity.start(context, movie)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieImage(
            imageBaseUrl + movie.posterImage,
            modifier = Modifier
                .height(itemHeight)
                .width(itemWidth)
                .offset(elementsAroundSpacing, 0.dp)
                .zIndex(2f)

        )
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(
                    0.dp,
                    elementsAroundSpacing * 1.5f,
                    elementsAroundSpacing,
                    elementsAroundSpacing / 2.5f
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .height(itemHeight),
            elevation = CARD_ELEVATION_LIGHT,
            shape = RoundedCornerShape(
                0.dp,
                CARD_CORNER_RADIUS,
                CARD_CORNER_RADIUS,
                CARD_CORNER_RADIUS
            )

        ) {

            Column(
                modifier = Modifier
                    .padding(
                        elementsAroundSpacing + CARD_CONTENT_PADDING,
                        CARD_CONTENT_PADDING,
                        CARD_CONTENT_PADDING,
                        CARD_CONTENT_PADDING
                    ),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                MovieTitle(
                    movie.title
                )
                RatingView(
                    rating = movie.averageRating,
                    numberOfRates = movie.voteCount,
                    orientation = Orientation.Vertical
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Release: ${movie.releaseDate}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.secondary
                    )
                }

            }
        }

    }
}



@Composable
fun MovieTitle(
    title: String,
    color: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        color = color,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun MovieImage(
    imageUrl: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CARD_ELEVATION_LIGHT
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
