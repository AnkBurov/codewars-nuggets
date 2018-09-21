package io.ankburov.codewars

import java.util.*

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val firstRiver = generateSequence(input.nextLong()) { it + getSumOfNumbers(it) }.iterator()
    val secondRiver = generateSequence(input.nextLong()) { it + getSumOfNumbers(it) }.iterator()

    var firstRiverNext = firstRiver.next()
    var secondRiverNext = secondRiver.next()

    while (true) {
        when {
            firstRiverNext > secondRiverNext -> secondRiverNext = secondRiver.next()
            firstRiverNext < secondRiverNext -> firstRiverNext = firstRiver.next()
            else -> {
                println(firstRiverNext)
                return
            }
        }
    }
}

private fun getSumOfNumbers(it: Long): Long {
    return it.toString()
            .splitToSequence("")
            .filterNot { it == "" }
            .map { it.toLong() }
            .reduce { acc, i -> acc + i }
}