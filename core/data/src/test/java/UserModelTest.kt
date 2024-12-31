import com.minhky.core.data.model.toFollowString
import kotlin.test.Test
import kotlin.test.assertEquals

class UserModelTest {

    @Test
    fun toFollowStringReturnsZeroWhenNull() {
        val result = (null as Long?).toFollowString()
        assertEquals("0", result)
    }

    @Test
    fun toFollowStringReturnsZeroWhenNegative() {
        val result = (-1L).toFollowString()
        assertEquals("0", result)
    }

    @Test
    fun toFollowStringReturnsExactNumberWhenLessThanOrEqualToTen() {
        val result = 5L.toFollowString()
        assertEquals("5", result)
    }

    @Test
    fun toFollowStringReturnsTenPlusWhenBetweenTenAndHundred() {
        val result = 50L.toFollowString()
        assertEquals("10+", result)
    }

    @Test
    fun toFollowStringReturnsHundredPlusWhenGreaterThanHundred() {
        val result = 150L.toFollowString()
        assertEquals("100+", result)
    }
}