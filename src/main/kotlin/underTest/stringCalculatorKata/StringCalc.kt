package underTest.stringCalculatorKata

class StringCalc {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            return 0
        }
         val parsed = Integer.parseInt(numbers)
         if (parsed < 0) {
             throw Exception("negatives not allowed")
         }
         return parsed
    }

}
