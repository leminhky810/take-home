import androidx.lifecycle.SavedStateHandle
import com.minhky.core.data.model.UserModel
import com.minhky.takehome.feature.profile.UserProfileUiState
import com.minhky.takehome.feature.profile.UserProfileViewModel
import com.minhky.takehome.ui.user.asUserUiModel
import com.minhky.core.domain.GetProfileUseCase
import com.minhky.takehome.core.result.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserProfileViewModelTest {

    @Mock
    private lateinit var getProfileUseCase: GetProfileUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getProfileUseCase = mock(GetProfileUseCase::class.java)
    }

    @Test
    fun uiStateIsLoadingWhenProfileIsLoading() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("loginUserName" to "testuser"))
        `when`(getProfileUseCase("testuser")).thenReturn(flow { emit(Result.Loading) })

        val viewModel = UserProfileViewModel(savedStateHandle, getProfileUseCase)

        assert(viewModel.uiState.value is UserProfileUiState.Loading)
    }

    @Test
    fun uiStateIsSuccessWhenProfileIsLoadedSuccessfully() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("loginUserName" to "testuser"))
        val userModel = UserModel(1, "testuser", "avatarUrl", "htmlUrl", "location", 100, 50)
        `when`(getProfileUseCase("testuser")).thenReturn(flow { emit(Result.Success(userModel)) })

        val viewModel = UserProfileViewModel(savedStateHandle, getProfileUseCase)

        assert(viewModel.uiState.value is UserProfileUiState.Success)
        assert((viewModel.uiState.value as UserProfileUiState.Success).data == userModel.asUserUiModel())
    }

    @Test
    fun uiStateIsErrorWhenProfileLoadFailsWithException() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("loginUserName" to "testuser"))
        `when`(getProfileUseCase("testuser")).thenReturn(flow { emit(Result.Error(Exception("Error message"))) })

        val viewModel = UserProfileViewModel(savedStateHandle, getProfileUseCase)

        assert(viewModel.uiState.value is UserProfileUiState.Error)
        assert((viewModel.uiState.value as UserProfileUiState.Error).message == "Error message")
    }

    @Test
    fun uiStateIsErrorWhenProfileLoadFailsWithNullMessage() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("loginUserName" to "testuser"))
        `when`(getProfileUseCase("testuser")).thenReturn(flow { emit(Result.Error(null)) })

        val viewModel = UserProfileViewModel(savedStateHandle, getProfileUseCase)

        assert(viewModel.uiState.value is UserProfileUiState.Error)
        assert((viewModel.uiState.value as UserProfileUiState.Error).message == "Unknown error")
    }

    @Test
    fun uiStateIsLoadingWhenLoginUserNameIsNull() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("loginUserName" to null))
        `when`(getProfileUseCase(null)).thenReturn(flow { emit(Result.Loading) })

        val viewModel = UserProfileViewModel(savedStateHandle, getProfileUseCase)

        assert(viewModel.uiState.value is UserProfileUiState.Loading)
    }
}