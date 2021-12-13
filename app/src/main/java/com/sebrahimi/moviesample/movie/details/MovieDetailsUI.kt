package com.sebrahimi.moviesample.movie.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.sebrahimi.moviesample.movie.list.MovieTitle
import com.sebrahimi.moviesample.ui.components.ExpandableText
import com.sebrahimi.moviesample.ui.components.RatingView
import com.sebrahimi.moviesample.ui.components.SingleVerticalIndicator
import com.sebrahimi.moviesample.ui.theme.*
import com.sebrahimi.moviesample.util.ColorUtil
import com.sebrahimi.moviesample.util.StringUtil
import com.sebrahimi.restapi.dto.MovieDTO
import com.sebrahimi.restapi.dto.MovieDetailDTO


val CHIP_BORDER_RADIUS = 12.dp
val ROW_BLOCK_SPACING = XXLARGE_SPACING

@Composable
fun MovieDetailsUI(imageBaseUrl: String,movie: MovieDTO, viewModel: MovieDetailsViewModel) {
    val movieDetailDTO = viewModel.movieDetails.value

    Poster(
        imageUrl = imageBaseUrl + (movie.backdropImage ?: ""),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .aspectRatio(1f)
    )
    DetailsContainer {
        AsyncViewContainer(movieDetailDTO) {
            ChipGroup(
                titles = it.genres.map { return@map it.name },
                color = MaterialTheme.colors.primary
            )
        }
        VerticalBlockSpacer()
        MovieDetailsTitle(title = movie.title, averageVote = movie.averageRating ?: 0f, numberOfVotes = movie.voteCount)
        VerticalBlockSpacer()
        MovieOverview(overView = movie?.overview ?: "No overview available !!!")
        VerticalBlockSpacer()
        AsyncViewContainer(movieDetailDTO = movieDetailDTO) {
            MovieDetailsTable(
                budget = it.budget,
                revenue = it.revenue,
                languages = it.spokenLanguages.map { lang -> lang.englishName })
        }
    }
}

@Composable
fun AsyncViewContainer(
    movieDetailDTO: MovieDetailDTO?,
    modifier: Modifier = Modifier
        .height(20.dp)
        .width(20.dp),
    content: @Composable (movieDetails: MovieDetailDTO) -> Unit
) {
    movieDetailDTO?.let {
        content(it)
    } ?: run {
        Column(modifier = Modifier.fillMaxWidth().padding(NORMAL_SPACING), horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                strokeWidth = 2.dp,
                modifier = modifier
            )
        }
    }
}

@Composable
fun DetailsContainer(content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp
    LazyColumn(
        modifier = Modifier
            .zIndex(2f)
    ) {
        item {
            Column(modifier = Modifier.padding(0.dp, width - 40.dp, 0.dp, 0.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 70.dp,
                    shape = RoundedCornerShape(45.dp, 45.dp, 0.dp, 0.dp),
                ) {
                    Column(modifier = Modifier.padding(CARD_CONTENT_PADDING_EXTENSIVE)) {
                        content()
                    }

                }
            }
        }
    }
}

@Composable
fun Poster(imageUrl: String, modifier: Modifier) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {

            }
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun Chip(text: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
    ) {
        Text(
            text = text, modifier = modifier
                .clip(shape = RoundedCornerShape(CHIP_BORDER_RADIUS))
                .background(ColorUtil.changeTransparency(color, 15))
                .padding(12.dp, 2.dp, 12.dp, 4.dp), color = color,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ChipGroup(titles: List<String>, color: Color) {
    FlowRow(mainAxisSpacing = 14.dp, crossAxisSpacing = 14.dp) {
        titles.forEach {
            Chip(text = it, color = color)
        }
    }
}

@Composable
fun MovieDetailsTitle(title: String, averageVote: Float, numberOfVotes: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SingleVerticalIndicator(
            color = ColorUtil.changeTransparency(
                MaterialTheme.colors.primary,
                60
            )
        )
        Column(
            modifier = Modifier.padding(NORMAL_SPACING, 0.dp, 0.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            MovieTitle(title = title, color = TextTitleColor)
            RatingView(
                rating = averageVote,
                numberOfRates = numberOfVotes,
                orientation = Orientation.Horizontal
            )
        }
    }

}

@Composable
fun MovieOverview(overView: String) {
    ExpandableText(
        text = overView,
        textStyle = MaterialTheme.typography.body2,
        textAlign = TextAlign.Justify,
        minimizedMaxLines = 5
    )
}

@Composable
fun MovieDetailsTable(budget: Long, revenue: Long, languages: List<String>) {
    val dividerColor = Color(0xFFEEEEEE)
    Card(
        shape = RoundedCornerShape(CARD_CORNER_RADIUS_EXTENSIVE),
        backgroundColor = LightGrayBackground,
        elevation = 0.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(CARD_CONTENT_PADDING)
        ) {
            MovieTableRow(key = "Budget", value = StringUtil.shortenMoneyNumber(budget))
            Divider(color = dividerColor, thickness = 1.dp)
            MovieTableRow(key = "Revenue", value = StringUtil.shortenMoneyNumber(revenue))
            Divider(color = dividerColor, thickness = 1.dp)
            MovieTableRow(key = "Languages", value = "${languages.joinToString(separator = ", ") { it }}")
        }
    }
}

@Composable
fun MovieTableRow(key: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = LARGE_SPACING)
    ) {
        Text(text = key, style = MaterialTheme.typography.subtitle2)
        Text(text = value, fontWeight = FontWeight.Black, fontSize = 14.sp)
    }
}

@Composable
fun VerticalBlockSpacer() {
    Spacer(modifier = Modifier.height(ROW_BLOCK_SPACING))
}
