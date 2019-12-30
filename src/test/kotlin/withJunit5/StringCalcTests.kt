package withJunit5
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import underTest.StringCalc

class StringCalcTests {
    @Test
    fun `add with empty string returns default`(){
        val sc = StringCalc()

        val result = sc.add("")

        assertEquals(0,result)
    }
}
