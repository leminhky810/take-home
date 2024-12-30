package com.minhky.takehome.feature.profile

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.minhky.takehome.R
import com.minhky.takehome.ui.state.EmptyState
import com.minhky.takehome.ui.HeaderItem
import com.minhky.takehome.designsystem.theme.ProjectTheme
import com.minhky.takehome.ui.DevicePreviews
import com.minhky.takehome.ui.state.LoadingState
import com.minhky.takehome.ui.user.FollowItem
import com.minhky.takehome.ui.user.LocationText
import com.minhky.takehome.ui.user.UserCard
import com.minhky.takehome.ui.user.UserUiModel
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun UserProfileRoute(
    onBack: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: UserProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    UserProfileScreen(
        state = state,
        onShowSnackbar = onShowSnackbar,
        onBack = onBack,
        modifier = modifier
    )
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun UserProfileScreen(
    state: UserProfileUiState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val label = stringResource(id = R.string.user_detail_label)

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HeaderItem(
            label = label,
            onBack = onBack,
            isShowBackButton = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        when (state) {
            UserProfileUiState.Loading -> LoadingState(modifier)
            UserProfileUiState.Error -> EmptyState(modifier)
            is UserProfileUiState.Success -> UserProfile(
                state.data,
                modifier
            )
        }
    }
}

@DevicePreviews
@Composable
fun UserProfileScreenPreview() {
    ProjectTheme {
        UserProfileScreen(
            state = fakeUserProfileUiState,
            onShowSnackbar = { _, _ -> false },
            onBack = {},
        )
    }
}

@Composable
private fun UserProfile(
    user: UserUiModel,
    modifier: Modifier = Modifier
) {
    val blogLabel = stringResource(id = R.string.blog)

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        UserCard(
            user,
            content = {
                user.location?.let {
                    LocationText(
                        location = it
                    )
                }
            },
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        val constraintSet = ConstraintSet {
            val follower = createRefFor("follower")
            val following = createRefFor("following")

            createHorizontalChain(follower, following, chainStyle = ChainStyle.Spread)

            constrain(follower) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            constrain(following) {
                top.linkTo(parent.top)
                start.linkTo(follower.end)
                end.linkTo(parent.end)
            }
        }
        ConstraintLayout(
            constraintSet = constraintSet,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FollowItem(
                count = stringResource(id = R.string.follower),
                text = user.followers,
                resId = R.drawable.core_ui_ic_follower,
                modifier = Modifier.layoutId("follower")
            )
            FollowItem(
                count = stringResource(id = R.string.following),
                text = user.following,
                resId = R.drawable.core_ui_ic_following,
                modifier = Modifier.layoutId("following")
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = blogLabel,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        user.htmlUrl?.let {
            Text(
                text = user.htmlUrl,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
