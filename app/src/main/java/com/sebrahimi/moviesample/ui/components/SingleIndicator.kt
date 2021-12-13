package com.sebrahimi.moviesample.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SingleVerticalIndicator(
    color: Color,
    modifier: Modifier = Modifier
) {

    Surface(color = color,
        modifier = modifier
            .width(3.dp)
            .height(50.dp),
        shape = RoundedCornerShape(14.dp)) {
    }
}