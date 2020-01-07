package withJunit5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import underTest.passwordCheckerKata.RuleChecker

class PasswordCheckerTests {
    @Test
    fun `check, with no rules, succeeds`(){
        val checker = RuleChecker(listOf())

        val result = checker.check("")

        assertThat(result).isTrue()
    }
    @Test
    fun `check, with one failing rule, fails`(){
        val checker = RuleChecker(listOf { _ -> false })

        val result = checker.check("")

        assertThat(result).isFalse()
    }
    @Test
    fun `check, with one passing rule, passes`(){
        val checker = RuleChecker(listOf { _ -> true })

        val result = checker.check("")

        assertThat(result).isTrue()
    }
    @Test
    fun `check, with one passing and one failing rule, fails`(){
        val checker =
            RuleChecker(listOf({ _ -> true }, { _ -> false }))

        val result = checker.check("")

        assertThat(result).isFalse()
    }
}

class PassRule_MinLengthTests{
    @Test
    fun `when under min size, returns false`(){
        val rule = RuleChecker.Rules.minLengthRule
        val result = rule("123456")
        assertThat(result).isFalse()
    }

   @Test
   fun `when over min size, returns true`(){
       val rule = RuleChecker.Rules.minLengthRule
       val result = rule("1234567")
       assertThat(result).isTrue()
   }
}
class PassRule_NumberRequired{
    @Test
    fun `when does not contain number, fails`(){
        val rule = RuleChecker.Rules.numberRequired
        val result = rule("abc")
        assertThat(result).isFalse()
    }

   @ParameterizedTest
   @CsvSource(
       "abc1",
       "1abc",
       "a1bc"
   )
   fun `when has number, passes`(input:String){
       val rule = RuleChecker.Rules.numberRequired
       val result = rule(input)
       assertThat(result).isTrue()
   }
}
