package withJunit5
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.StringCalcWithTime
import java.util.*

class TestableStringCalcWithTime(val currentDayWillBe: Int) : StringCalcWithTime(){

    override fun getCurrentDay(): Int {
        return currentDayWillBe
    }

}

class StringCalcWithTimeTests {
    fun makeCalc(day:Int): TestableStringCalcWithTime{
        return TestableStringCalcWithTime(day)
    }

    fun makeEnabledCalc(): TestableStringCalcWithTime{
        return makeCalc(Calendar.MONDAY)
    }

    @Test
    fun `adding a number on SUNDAYS returns ignored result`(){
        val sc = makeCalc(Calendar.SUNDAY)

        val result = sc.add("")

        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `Add with empty string returns default`(){
        val sc = makeEnabledCalc()

        val result = sc.add("")

        assertEquals(0,result)
    }

    @Test
    fun `with single number returns that number`(){
        val sc = makeEnabledCalc()

        val result = sc.add("1")

        assertEquals(1, result)
    }
}
