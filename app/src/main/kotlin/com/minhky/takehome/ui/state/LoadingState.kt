package com.minhky.takehome.ui.state

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.minhky.takehome.designsystem.component.LoadingWheel
import com.minhky.takehome.designsystem.theme.ProjectTheme

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    LoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        contentDesc = "",
    )
}

@Preview
@Composable
private fun LoadingStatePreview() {
    ProjectTheme {
        LoadingState()
    }
}