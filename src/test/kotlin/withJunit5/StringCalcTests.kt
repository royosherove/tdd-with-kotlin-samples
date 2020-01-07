package withJunit5
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import underTest.stringCalculatorKata.StringCalc

class StringCalcTests {
    fun makeCalc(): StringCalc {return StringCalc()
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

    @ParameterizedTest
    @CsvSource(
        "1,1",
        "2,2",
        "10,10"
    )
    fun `with parameters`(input:String, expected:Int){
        val sc = makeCalc()
        val result = sc.add(input)
        assertEquals(expected, result)
    }
}
