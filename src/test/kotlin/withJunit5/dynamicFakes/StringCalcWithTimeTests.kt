package withJunit5.dynamicFakes
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.extractAndOverride.StringCalcWithTime
import java.util.*


class StringCalcWithTimeTests {
    object Helpers{

        fun makeCalc(): StringCalcWithTime {
            return StringCalcWithTime()
        }

        fun fakeStaticCalendarDayOfWeek(day: Int) {
            mockkStatic(Calendar::class)
            every { Calendar.getInstance().get(Calendar.DAY_OF_WEEK) } returns day
        }

        fun makeCalcWithEnabledDateDepenendecy(): StringCalcWithTime {
            fakeStaticCalendarDayOfWeek(Calendar.MONDAY)
            return makeCalc()
        }

    }
    @AfterEach
    fun `teardown`(){
        unmockkAll()
    }

    @Test
    fun `adding a number on SUNDAYS returns ignored result`(){
        Helpers.fakeStaticCalendarDayOfWeek(Calendar.SUNDAY)
        val sc = Helpers.makeCalc()

        val result = sc.add("")

        assertThat(result).isEqualTo(-1)
    }


    @Test
    fun `Add with empty string returns default`(){
        val sc = Helpers.makeCalcWithEnabledDateDepenendecy()

        val result = sc.add("")

        assertEquals(0,result)
    }


    @Test
    fun `with single number returns that number`(){
        val sc = Helpers.makeCalcWithEnabledDateDepenendecy()

        val result = sc.add("1")

        assertEquals(1, result)
    }
}
