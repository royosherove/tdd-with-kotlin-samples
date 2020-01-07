package underTest.extractAndOverride

import underTest.stringCalculatorKata.StaticLogger

open class StringCalcWithStatics {
     fun add(numbers: String): Int {
        if (numbers.isEmpty()){
            callLogger("0")
            return 0
        }
        return Integer.parseInt(numbers)
    }

    internal open fun callLogger(text:String) {
        StaticLogger.Instance.write(text)
    }

}
