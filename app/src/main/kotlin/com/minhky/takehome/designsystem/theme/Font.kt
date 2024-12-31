package com.minhky.takehome.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.minhky.takehome.R

val DefaultFont = FontFamily(
    Font(
        resId = R.font.notosans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.notosans_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.notosans_bold,
        weight = FontWeight.Bold
    )
)