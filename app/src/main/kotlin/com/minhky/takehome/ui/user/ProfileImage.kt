package com.minhky.takehome.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.minhky.takehome.R
import com.minhky.takehome.ui.state.LoadingState

@Composable
fun ProfileImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current

    Card(
        modifier = modifier
            .size(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 2.dp,
                    end = 2.dp,
                    top = 4.dp,
                    bottom = 4.dp
                )
            ,
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                // Display a progress bar while loading
                LoadingState(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                )
            }

            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
                painter = if (isError.not() && !isLocalInspection) {
                    imageLoader
                } else {
                    painterResource(R.drawable.core_designsystem_image_placeholder)
                },
                contentDescription = null,
            )
        }
    }
}