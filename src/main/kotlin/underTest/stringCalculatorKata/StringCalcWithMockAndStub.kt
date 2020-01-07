package underTest.stringCalculatorKata

import underTest.stringCalculatorKata.interfaces.MyLogger
import underTest.stringCalculatorKata.interfaces.MyService
import java.lang.Exception

class StringCalcWithMockAndStub (val logger: MyLogger, val service: MyService) {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            try {
                logger.write("0")
            }
            catch (ex:Exception){
                service.notify(ex.message)
            }
            return 0
        }
        return Integer.parseInt(numbers)
    }

}
