//package withSpek
//
///**
// * HamKrest
// * Expekt
// * Kluent
// */
//import org.assertj.core.api.Assertions.assertThat
//import org.spekframework.spek2.Spek
//import org.spekframework.spek2.style.specification.describe
//import underTest.StringCalc
//
//class StringCalcWithSpekTests :Spek({
//    describe("add"){
//        context("with no numbers") {
//            it("returns zero") {
//                val sc = StringCalc()
//                val result = sc.add("")
//                assertThat(result).isEqualTo(0)
//            }
//        }
//    }
//
//})
