package com.minhky.takehome.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.minhky.takehome.R

val LatoFont = FontFamily(
    Font(
        resId = R.font.lato_light,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.lato_regular,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.lato_bold,
        weight = FontWeight.Bold
    )
)