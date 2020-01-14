package withKotlinTest

import io.kotlintest.be
import io.kotlintest.data.forall
import io.kotlintest.matchers.haveSubstring
import io.kotlintest.should
import io.kotlintest.shouldThrow
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.specs.DescribeSpec
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import underTest.stringCalculatorKata.StringCalc
import java.lang.Exception

class StringCalcKTTests : AnnotationSpec(){

    @Test
    fun `add with empty string returns default`(){
        val sc = StringCalc()

        val result = sc.add("")

        result should be(0)
    }
}

class StringCalcKTTests2 : StringSpec({
    "add with empty string returns default" {
        val sc = StringCalc()

        val result = sc.add("")

        result should be(0)
    }

    "using it with lamdas" {
        "abcd".split(',').forEach {
            println(it + "a")
        }
    }

    "add with negative, throws" {
       val sc = StringCalc()

        val ex = shouldThrow<Exception> { sc.add("-1")  }
        ex.message should haveSubstring("negatives not allowed")
    }

    "add with single number returns that number" {
        forall(
            row("1", 1),
            row("2", 2)
        ){ input:String,expected:Int ->

            val sc = StringCalc()

            val result = sc.add(input)

            result should be(expected)
        }
    }
})

class StringCalcKTTests3: DescribeSpec({
    describe("add"){
        context("with empty strings"){
            val sc = StringCalc()

            val result = sc.add("")

            it("returns the default value"){
                result should be(0)
            }
        }
    }
})


