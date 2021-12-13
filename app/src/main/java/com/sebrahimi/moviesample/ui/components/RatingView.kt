package com.sebrahimi.moviesample.ui.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.sebrahimi.moviesample.movie.list.ratingBarSize

@Composable
fun RatingView(
    rating: Float,
    numberOfRates: Int,
    orientation: Orientation = Orientation.Vertical,
    modifier: Modifier = Modifier
) {
    if (orientation == Orientation.Vertical) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Column() {
                RatingRow(rating = rating, numberOfRates = numberOfRates)
            }
        }
    } else {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            RatingRow(rating = rating, numberOfRates = numberOfRates, voteModifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp))
        }
    }
}

@Composable
fun RatingRow(
    rating: Float,
    numberOfRates: Int,
    voteModifier: Modifier = Modifier
) {
    RatingBar(
        value = rating / 2f,
        size = ratingBarSize,
        stepSize = StepSize.HALF,
        ratingBarStyle = RatingBarStyle.Normal,
        onValueChange = {

        }) {
    }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = voteModifier) {
        Text(
            text = "${rating}",
            style = MaterialTheme.typography.subtitle2,
        )
        Text(
            text = " (${numberOfRates})",
            style = MaterialTheme.typography.caption,
        )
    }
}