package com.minhky.takehome.feature.home

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.minhky.takehome.R
import com.minhky.takehome.designsystem.component.HyperlinkText
import com.minhky.takehome.designsystem.theme.ProjectTheme
import com.minhky.takehome.ui.DevicePreviews
import com.minhky.takehome.ui.HeaderItem
import com.minhky.takehome.ui.state.LoadingState
import com.minhky.takehome.ui.user.UserCard
import com.minhky.takehome.ui.user.UserUiModel

@Composable
fun HomeRoute(
    onUserProfileClick: (String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val userPaging = viewModel.userPaging.collectAsLazyPagingItems()
    HomeScreen(
        userPaging = userPaging,
        onShowSnackbar = onShowSnackbar,
        onUserProfileClick = onUserProfileClick,
        modifier = modifier
    )
}


@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun HomeScreen(
    userPaging: LazyPagingItems<UserUiModel>,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    onUserProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val label = stringResource(id = R.string.home_label)

    LaunchedEffect(key1 = userPaging.loadState) {
        if(userPaging.loadState.refresh is LoadState.Error) {
            onShowSnackbar(
                "Error: " + (userPaging.loadState.refresh as LoadState.Error).error.message,
                null
            )
        }
    }

    HomeScreenComponent(
        label = label,
        userPaging = userPaging,
        onUserProfileClick = onUserProfileClick,
        modifier = modifier
    )
}

@Composable
internal fun HomeScreenComponent(
    label: String,
    userPaging: LazyPagingItems<UserUiModel>,
    onUserProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HeaderItem(
            label = label,
            onBack = {},
            isShowBackButton = false
        )
        Box(modifier = Modifier.fillMaxSize()) {
            if(userPaging.loadState.refresh is LoadState.Loading) {
                LoadingState(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(userPaging.itemCount) { position ->
                        userPaging[position]?.let {
                            if (position == 0) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            UserItem(
                                user = it,
                                onClick = onUserProfileClick
                            )
                        }
                    }
                    item {
                        if(userPaging.loadState.append is LoadState.Loading) {
                            LoadingState()
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun HomeScreenTest(
    homeState: HomeUiState,
    modifier: Modifier = Modifier
) {
    val label = stringResource(id = R.string.home_label)
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HeaderItem(
            label = label,
            onBack = {},
            isShowBackButton = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        UsersList(
            (homeState as HomeUiState.Success).users,
            {  },
            modifier
        )
    }
}

@DevicePreviews
@Composable
fun HomeScreenPreview() {
    ProjectTheme {
        HomeScreenTest(
            homeState = fakeHomeUiState,
        )
    }
}


@Composable
private fun UsersList(
    list: List<UserUiModel>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(list.size) { position ->
            UserItem(
                user = list[position],
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun UserItem(
    user: UserUiModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserCard(
            user,
            content = {
                user.htmlUrl?.let {
                    HyperlinkText(
                        url = it
                    )
                }
            },
            onClick = onClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}