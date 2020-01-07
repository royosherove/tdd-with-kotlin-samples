package underTest.passwordCheckerKata

class RuleChecker(val rules: List<(s:String) -> Boolean>){
    fun check(password: String): Boolean {
        if (rules.isEmpty()) {
            return true
        }
        val failed = rules.map { rule -> rule(password) }
                        .filter { it == false }
        return failed.isEmpty()
    }

}
