package com.minhky.takehome.ui.user

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.minhky.takehome.R
import com.minhky.takehome.designsystem.theme.ProjectTheme

@Composable
fun FollowItem(
    count: String,
    text: String,
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = count,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }


}

@PreviewLightDark
@Composable
fun FollowerItemPreview() {
    ProjectTheme {
        FollowItem(
            count = "100+",
            resId = R.drawable.core_ui_ic_follower,
            text = "Follower"
        )
    }
}

@PreviewLightDark
@Composable
fun FollowingItemPreview() {
    ProjectTheme {
        FollowItem(
            count = "100+",
            resId = R.drawable.core_ui_ic_following,
            text = "Following"
        )
    }
}