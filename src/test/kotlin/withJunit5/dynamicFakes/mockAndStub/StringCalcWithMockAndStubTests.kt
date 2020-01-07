package withJunit5.dynamicFakes.mockAndStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.StringCalcWithMockAndStub
import underTest.interfaces.MyLogger
import underTest.interfaces.MyService


class StringCalcWithMockAndStubTests {
    inline fun <reified T:Any> fake(): T {
        return mockk<T>(relaxed = true, relaxUnitFun = true)
    }

    fun makeCalc(logger: MyLogger = fake(),
                 service: MyService = fake()
    ): StringCalcWithMockAndStub {
        return StringCalcWithMockAndStub(logger, service)
    }

    @Test
    fun `call add when logger throws notifies service`(){
        val stubLog = fake<MyLogger>()
        every { stubLog.write(any())}
                .throws(Exception("fake exception"))

        val mockService = fake<MyService>()
        val sc = makeCalc(stubLog,mockService)

        sc.add("")

        verify { mockService.notify("fake exception") }
    }
    @Test
    fun `calling add invokes the logger`(){
        val mockLog = fake<MyLogger>()
        val sc = makeCalc(mockLog)
        sc.add("")

        verify { mockLog.write("0") }
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
