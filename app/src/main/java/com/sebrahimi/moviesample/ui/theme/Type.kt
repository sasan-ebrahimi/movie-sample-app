package com.sebrahimi.moviesample.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sebrahimi.moviesample.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.opensans)),
    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.notosans)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    subtitle2 = TextStyle(
        color = TextSubtitleColor1
    ),
    caption = TextStyle(
        color = TextSubtitleColor1,
        fontSize = 10.sp
    )

)



