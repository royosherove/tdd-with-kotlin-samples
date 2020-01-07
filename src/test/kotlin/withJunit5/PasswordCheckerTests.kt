package withJunit5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
