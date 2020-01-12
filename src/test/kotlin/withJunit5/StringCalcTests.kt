package withJunit5
import io.kotlintest.data.forall
import io.kotlintest.tables.row
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import underTest.stringCalculatorKata.StringCalc
import java.util.*

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
    fun `Add with negative number, throws`(){
        val sc = makeCalc()
        val ex = assertThrows<Exception>(){
            sc.add("-1")
        }
        assertThat(ex.message).matches { it.contains("negatives not allowed")}
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
