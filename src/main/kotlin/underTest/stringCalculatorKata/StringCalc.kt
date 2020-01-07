package underTest.stringCalculatorKata

class StringCalc {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            return 0
        }
        return Integer.parseInt(numbers)
    }

}
