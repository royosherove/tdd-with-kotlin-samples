package withJunit5
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import underTest.MyLogger
import underTest.StringCalcWithMock

class FakeLogger:MyLogger{
    var written = ""
    override fun write(text: String) {
        written = text
    }

}
class StringCalcWithMockTests {
    fun makeCalc(logger: MyLogger = FakeLogger()): StringCalcWithMock {
        return StringCalcWithMock(logger)
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
