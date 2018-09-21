package io.ankburov.codewars

import java.math.BigDecimal
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun main(args: Array<String>) {
    val cities = mutableListOf(9 to 12, 24 to 15, 12 to 30, 4 to 3, 13 to 27)

    var travelledDistance = 0L
    val firstCity = cities.first()
    var currentPosition = firstCity
    cities.remove(firstCity)

    println("Starting from $currentPosition")
    while (cities.isNotEmpty()) {
        cities.minBy { to -> getEuclideanDistance(currentPosition, to) }
                ?.let { to ->
                    travelledDistance += getEuclideanDistance(currentPosition, to)
                    cities.remove(to)
                    currentPosition = to
                    println("End up in $to, travelled distance is $travelledDistance")
                }
    }
    travelledDistance += getEuclideanDistance(currentPosition, firstCity)
    currentPosition = firstCity
    println("End up in $currentPosition, travelled distance is $travelledDistance")

    println(travelledDistance)


    /*val minDistance = cities.map {
        var travelledDistance = 0L
        var currentPosition = it
        val checkingCities = ArrayList(cities)
        while (checkingCities.isNotEmpty()) {
            checkingCities.minBy { to -> getEuclideanDistance(currentPosition, to) }
                    ?.let { to ->
                        travelledDistance += getEuclideanDistance(currentPosition, to)
                        checkingCities.remove(to)
                        currentPosition = to
                    }
        }
        travelledDistance += getEuclideanDistance(currentPosition, it)
        travelledDistance
    }.min()

    println(minDistance)*/

}

private fun getEuclideanDistance(from: Pair<Int, Int>, to: Pair<Int, Int>): Long {
    return Math.round(Math.sqrt(Math.pow((to.first - from.first).toDouble(), 2.0) + Math.pow((to.second - from.second).toDouble(), 2.0)))
}