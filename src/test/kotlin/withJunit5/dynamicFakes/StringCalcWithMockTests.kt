package withJunit5.dynamicFakes
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import underTest.stringCalculatorKata.interfaces.MyLogger
import underTest.stringCalculatorKata.StringCalcWithMock

class StringCalcWithMockTests {
    fun makeCalc(logger: MyLogger = mockk(relaxed = true)): StringCalcWithMock {
        return StringCalcWithMock(logger)
    }

    @Test
    fun `calling add invokes the logger`(){
        val mockLog = mockk<MyLogger>(relaxed = true)
        val sc = makeCalc(mockLog)
        sc.add("")

        verify { mockLog.write("0")}
    }

    @Test
    fun `Add with empty string returns default`(){
        val sc = makeCalc()

        val result = sc.add("")

        assertEquals(0,result)
    }
    @Test
    fun `with single number returns that number`(){
        val sc = makeCalc()
        val result = sc.add("1")
        assertEquals(1, result)
    }


}
