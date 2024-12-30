package com.minhky.takehome.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.minhky.takehome.R
import com.minhky.takehome.designsystem.component.HyperlinkText
import com.minhky.takehome.designsystem.theme.ProjectTheme

@Composable
fun UserCard(
    userUiModel: UserUiModel,
    onClick: (Long) -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true
            )
            .clickable {
                onClick(userUiModel.id)
            }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
        ) {
            ProfileImage(
                imageUrl = userUiModel.avatarUrl
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                userUiModel.login?.let {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = it
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                content()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun UserCardProfilePreview() {
    ProjectTheme {
        UserCard(
            fakeUserUiModel,
            content = {
                fakeUserUiModel.location?.let {
                    LocationText(
                        location = it
                    )
                }
            }, onClick = {}
        )
    }
}


@PreviewLightDark
@Composable
fun UserCardItemPreview() {
    ProjectTheme {
        UserCard(
            fakeUserUiModel,
            content = {
                fakeUserUiModel.htmlUrl?.let {
                    HyperlinkText(
                        url = it
                    )
                }
            }, onClick = {}
        )
    }
}

@Composable
fun LocationText(
    location: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .size(15.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}