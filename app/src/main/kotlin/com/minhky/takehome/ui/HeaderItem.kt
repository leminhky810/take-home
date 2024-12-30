package com.minhky.takehome.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.minhky.takehome.designsystem.icon.IconsApp
import com.minhky.takehome.designsystem.theme.ProjectTheme

@Composable
fun HeaderItem(
    label: String,
    onBack: () -> Unit,
    isShowBackButton: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        if (isShowBackButton) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = IconsApp.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@PreviewLightDark
@Composable
fun HeaderItemPreview() {
    ProjectTheme {
        HeaderItem(
            label = "Users Detail",
            onBack = {}
        )
    }
}