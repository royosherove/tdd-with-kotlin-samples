package underTest.passwordCheckerKata

class RuleChecker(val rules: List<(s:String) -> Boolean>){
    object Rules {
        val minLengthRule = {x:String -> x.length>6}
        val numberRequired = {x:String -> x.matches(".*[0-9].*".toRegex())}
    }

    fun check(password: String): Boolean {
        if (rules.isEmpty()) {
            return true
        }
        val failed = rules.map { rule -> rule(password) }
                        .filter { it == false }
        return failed.isEmpty()
    }

}
