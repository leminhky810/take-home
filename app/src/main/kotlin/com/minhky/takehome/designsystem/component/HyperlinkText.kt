package com.minhky.takehome.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.minhky.takehome.designsystem.theme.HyperlinkColor

@Composable
fun HyperlinkText(
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HyperlinkColor, textDecoration = TextDecoration.Underline)) {
            append(url)
        }
        addStringAnnotation(
            tag = "URL",
            annotation = url,
            start = 0,
            end = url.length
        )
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier.clickable {
            uriHandler.openUri(url)
        }
    )
}

@PreviewLightDark
@Composable
fun HyperlinkTextPreview() {
    HyperlinkText(url = "https://example.com")
}