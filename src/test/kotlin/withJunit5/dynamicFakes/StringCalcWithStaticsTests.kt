package withJunit5.dynamicFakes
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.stringCalculatorKata.StaticLogger
import underTest.extractAndOverride.StringCalcWithStatics

class StringCalcWithStaticsTests {
    object Helpers{
        fun makeCalc(): StringCalcWithStatics {
            return StringCalcWithStatics()
        }

        fun fakeStaticLogger() {
            mockkObject(StaticLogger.Instance)
            every { StaticLogger.Instance.write(any()) } just Runs
        }
    }

    @AfterEach
    fun `teardown`(){
        unmockkAll()
    }

    @Test
    fun `EXTENSION FUNCTIONS adding a number calls the logger`(){
        Helpers.fakeStaticLogger()
        val sc = Helpers.makeCalc()
        sc.add("")

        verify { StaticLogger.Instance.write("0") }
    }


    @Test
    fun `Add with empty string returns default`(){
        Helpers.fakeStaticLogger()
        val sc = Helpers.makeCalc()

        val result = sc.add("")

        assertEquals(0,result)
    }

    @Test
    fun `with single number returns that number`(){
        val sc = Helpers.makeCalc()

        val result = sc.add("1")

        assertEquals(1, result)
    }
}
