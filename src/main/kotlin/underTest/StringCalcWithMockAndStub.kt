package underTest

import underTest.interfaces.MyLogger
import underTest.interfaces.MyService

class StringCalcWithMockAndStub (val logger: MyLogger, val service: MyService) {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            logger.write("0")
            return 0
        }
        return Integer.parseInt(numbers)
    }

}
