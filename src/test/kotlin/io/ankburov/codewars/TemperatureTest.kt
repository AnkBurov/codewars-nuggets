package io.ankburov.codewars

import org.junit.Test

class TemperatureTest {

    /*1
temp is -2
temp is -8
temp is 4
temp is 5*/

    @Test
    fun test() {
        val temperatures = listOf(1, -2, -8, 4, 5)


        var theClosestToZero: Pair<Int, Int>? = null // temperature - difference
        for (i in 0 until temperatures.size) {
//            val t = input.nextInt() // a temperature expressed as an integer ranging from -273 to 5526
            val t = temperatures[i] // a temperature expressed as an integer ranging from -273 to 5526
            val diff = Math.abs(0 - t)
            when {
                theClosestToZero == null -> theClosestToZero = t to diff
                diff == theClosestToZero.second && t > 0 -> theClosestToZero = t to diff
                diff < theClosestToZero.second -> theClosestToZero = t to diff
            }
        }

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");

        theClosestToZero?.first.let { println(it) }
    }

    @Test
    fun name() {
        //2500
        //2500 / 4 = 625
        val altitude = 3;

//        if (speed > 40) {
//            4
//        } else {
//            0
//        }

        when {
            altitude > 1875 -> 1
            altitude > 1250 -> 2
            altitude > 625 -> 3
            else -> 4
        }


        val deleted = 4000 / 4
        println(deleted)
    }
}