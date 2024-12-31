import com.minhky.core.data.repo.UserRepositoryImp
import com.minhky.core.data.result.ResourceResult
import com.minhky.core.network.model.response.UserResponse
import com.minhky.core.network.service.UserService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.mock.Calls

class UserRepositoryImpTest {

    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepositoryImp

    @Before
    fun setUp() {
        userService = mock(UserService::class.java)
        userRepository = UserRepositoryImp(userService)
    }

    @Test
    fun getProfileReturnsUserProfileSuccessfully() = runBlocking {
        val userName = "testuser"
        val userResponse = UserResponse(1, "testuser", "avatarUrl", "htmlUrl", "location", 100, 50)
        `when`(userService.getProfile(userName)).thenReturn(Calls.response(userResponse))

        val result = userRepository.getProfile(userName)

        assert(result is ResourceResult.Success)
        assertEquals((result as ResourceResult.Success).data, userResponse)
    }

}