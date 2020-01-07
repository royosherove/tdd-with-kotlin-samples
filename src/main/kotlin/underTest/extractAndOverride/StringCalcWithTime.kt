package underTest.extractAndOverride

import java.util.*


open class StringCalcWithTime {
     fun add(numbers: String): Int {
         val day = getCurrentDay()
         //we don't work on sundays
         if (day == Calendar.SUNDAY) {
             return -1
         }

         if (numbers.isEmpty()){
            return 0
        }
        return Integer.parseInt(numbers)
    }

    protected open fun getCurrentDay(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }



}
