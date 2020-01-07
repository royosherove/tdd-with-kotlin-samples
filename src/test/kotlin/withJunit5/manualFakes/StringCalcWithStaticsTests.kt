package withJunit5.manualFakes
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import underTest.extractAndOverride.StringCalcWithStatics

class TestableStringCalcWithStatics: StringCalcWithStatics(){
    var writtenTo ="";
    override fun callLogger(text:String) {
        writtenTo = text

    }

}

class StringCalcWithStaticsTests {
    fun makeCalc(): TestableStringCalcWithStatics {
        return TestableStringCalcWithStatics()
    }

    @Test
    fun `adding a number calls the logger`(){
        val sc = makeCalc()

        sc.add("")

        assertThat(sc.writtenTo).contains("0")
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
