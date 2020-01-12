@file:Suppress("DuplicatedCode")

package underTest.higherOrder

import java.util.*


fun configureAdd(getDayFn: () -> Int)
        : (input: String) -> Int {

    return {
        when (getDayFn()){
            Calendar.SUNDAY -> throw Exception("Weekend!")
            else -> Integer.parseInt(it)
        }
    }
}

