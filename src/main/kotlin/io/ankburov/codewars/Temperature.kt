package io.ankburov.codewars

import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt() // the number of temperatures to analyse

    var theClosestToZero: Pair<Int, Int>? = null // temperature - difference
    for (i in 0 until n) {
        val t = input.nextInt() // a temperature expressed as an integer ranging from -273 to 5526
        val diff = Math.abs(0 - t)
        when {
            theClosestToZero == null -> theClosestToZero = t to diff
            diff == theClosestToZero.second && t > 0 -> theClosestToZero = t to diff
            diff < theClosestToZero.second -> theClosestToZero = t to diff
        }
    }
    println(theClosestToZero?.first ?: 0)
}