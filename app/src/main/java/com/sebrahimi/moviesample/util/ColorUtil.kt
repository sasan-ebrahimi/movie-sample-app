package com.sebrahimi.moviesample.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import java.lang.Exception

object ColorUtil {

    fun changeTransparency(color: Color, transparency: Int): Color {
       return try {
           Color(ColorUtils.setAlphaComponent(color.toArgb(), transparency))
       }catch (e:Exception){
           color
       }
    }
}