import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.minhky.takehome.feature.profile.UserProfileUiState
import com.minhky.takehome.feature.profile.UserProfileScreen
import com.minhky.takehome.ui.user.UserUiModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysLoadingState_whenStateIsLoading() {
        composeTestRule.setContent {
            UserProfileScreen(
                state = UserProfileUiState.Loading,
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("Loading...").assertExists()
    }

    @Test
    fun displaysErrorState_whenStateIsError() {
        val errorMessage = "An error occurred"
        composeTestRule.setContent {
            UserProfileScreen(
                state = UserProfileUiState.Error(errorMessage),
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertExists()
    }

    @Test
    fun displaysUserProfile_whenStateIsSuccess() {
        val user = UserUiModel(
            id = 1,
            login = "testuser",
            avatarUrl = "https://example.com/avatar.png",
            htmlUrl = "https://example.com",
            location = "Test Location",
            followers = "100",
            following = "50"
        )
        composeTestRule.setContent {
            UserProfileScreen(
                state = UserProfileUiState.Success(user),
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("testuser").assertExists()
        composeTestRule.onNodeWithText("Test Location").assertExists()
        composeTestRule.onNodeWithText("100").assertExists()
        composeTestRule.onNodeWithText("50").assertExists()
        composeTestRule.onNodeWithText("https://example.com").assertExists()
    }

    @Test
    fun displaysEmptyState_whenUserLocationIsNull() {
        val user = UserUiModel(
            id = 1,
            login = "testuser",
            avatarUrl = "https://example.com/avatar.png",
            htmlUrl = "https://example.com",
            location = null,
            followers = "100",
            following = "50"
        )
        composeTestRule.setContent {
            UserProfileScreen(
                state = UserProfileUiState.Success(user),
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("testuser").assertExists()
        composeTestRule.onNodeWithText("100").assertExists()
        composeTestRule.onNodeWithText("50").assertExists()
        composeTestRule.onNodeWithText("https://example.com").assertExists()
    }
}