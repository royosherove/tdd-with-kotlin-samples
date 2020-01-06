package underTest

class StringCalcWithMock (val logger:MyLogger) {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            logger.write("0")
            return 0
        }
        return Integer.parseInt(numbers)
    }

}
