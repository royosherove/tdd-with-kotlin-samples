package withJunit5

import io.kotlintest.data.forall
import io.kotlintest.tables.row
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import underTest.higherOrder.configureAdd
import java.util.*

class AddFnTests {
    @Test
    fun `addition on SUNDAY throws `(){
        val addFn = configureAdd() { Calendar.SUNDAY}
        assertThrows<Exception> { addFn("1") }
    }

    @ParameterizedTest
    @CsvSource(
        "Calendar.MONDAY,2, 1, 1",
        "Calendar.MONDAY, 2, 2 , 2"
    )
    fun `addition on a weekday works as expected `(dayDesc:String, day:Int, input:String, expected:Int){
        val addFn = configureAdd() { day}
        val result = addFn(input)
        assertEquals(expected,result)
    }
    @Test
    fun `with parameters KT`(){ forall(
            row(Calendar.MONDAY, "1", 1),
            row(Calendar.TUESDAY, "1", 1)
        ){ day:Int, input:String, expected:Int ->
            val addFn = configureAdd() { day}
            val result = addFn(input)
            assertEquals(expected,result)
        }
    }


}
