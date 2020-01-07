package withJunit5.dynamicFakes
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.StringCalcWithStatics

class ExtractAndOverrideWitmockkTests {
    object Helpers{
        fun makeCalcWithDisabledLogger(): StringCalcWithStatics {
            val sc = spyk<StringCalcWithStatics>(recordPrivateCalls = true)
            every { sc.callLogger(any()) } just Runs
            return sc
        }

    }

    @AfterEach
    fun `teardown`(){
        unmockkAll()
    }

    @Test
    fun `extract & override with protected function`(){
        val sc = spyk<StringCalcWithStatics>(recordPrivateCalls = true)
        every { sc["callLogger"](any<String>())as Unit} just Runs
        sc.add("")

        verify { sc["callLogger"]("0")}
    }

    @Test
    fun `extract & override with internal function`(){
        val sc = spyk<StringCalcWithStatics>(recordPrivateCalls = true)
        every { sc.callLogger(any()) } just Runs
        sc.add("")

        verify { sc.callLogger(match { it.contains("0") })}
    }


    @Test
    fun `Add with empty string returns default`(){
        //spy built into helper function
        val sc = Helpers.makeCalcWithDisabledLogger()

        val result = sc.add("")

        assertEquals(0,result)
    }

    @Test
    fun `with single number returns that number`(){
        val sc = Helpers.makeCalcWithDisabledLogger()

        val result = sc.add("1")

        assertEquals(1, result)
    }
}
