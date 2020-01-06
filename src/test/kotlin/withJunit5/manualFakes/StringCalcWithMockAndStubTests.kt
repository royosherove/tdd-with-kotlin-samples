package withJunit5.manualFakes.mockAndStub
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.StringCalcWithMockAndStub
import underTest.interfaces.MyLogger
import underTest.interfaces.MyService

class FakeLogger: MyLogger {
    lateinit var toThrow: Exception
    var written = ""
    override fun write(text: String) {
        written = text
        throw toThrow
    }

    fun willThrow(exception: Exception) {
       toThrow = exception
    }
}

class FakeService: MyService{
    var notified: String? = null

    override fun notify(message: String?) {
        notified = message
    }
}

class StringCalcWithMockAndStubTests {
    fun makeCalc(logger: MyLogger = FakeLogger(),
                 service: MyService = FakeService()
    ): StringCalcWithMockAndStub {
        return StringCalcWithMockAndStub(logger, service)
    }

    @Test
    fun `call add when logger throws notifies service`(){
        val stubLog = FakeLogger()
        stubLog.willThrow(Exception("fake exception"))
        val mockService = FakeService()
        val sc = makeCalc(stubLog,mockService)

        sc.add("")

        assertThat(mockService.notified).contains("fake exception")
    }
    @Test
    fun `calling add invokes the logger`(){
        val mockLog = FakeLogger()
        val sc = makeCalc(mockLog)
        sc.add("")

        assertThat(mockLog.written).contains("0")
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
