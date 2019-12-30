package withKotlinTest

import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec
import io.kotlintest.specs.DescribeSpec
import io.kotlintest.specs.StringSpec
import underTest.StringCalc

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
