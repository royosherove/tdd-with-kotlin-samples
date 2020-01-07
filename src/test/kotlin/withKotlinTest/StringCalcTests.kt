package withKotlinTest

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.specs.DescribeSpec
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import underTest.stringCalculatorKata.StringCalc

class StringCalcKTTests : AnnotationSpec(){
    @Test
    fun `add with empty string returns default`(){
        val sc = StringCalc()

        val result = sc.add("")

        result.shouldBe(0)
    }
}

class StringCalcKTTests2 : StringSpec({
    "add with empty string returns default" {
        val sc = StringCalc()

        val result = sc.add("")

        result.shouldBe(0)
    }

    "add with single number returns that number" {
        forall(
            row("1", 1),
            row("2", 2)
        ){ input:String,expected:Int ->

            val sc = StringCalc()

            val result = sc.add(input)

            result.shouldBe(expected)
        }
    }
})

class StringCalcKTTests3: DescribeSpec({
    describe("add"){
        context("with empty strings"){
            val sc = StringCalc()

            val result = sc.add("")

            it("returns the dfault value"){
                result.shouldBe(0)
            }
        }
    }
})
