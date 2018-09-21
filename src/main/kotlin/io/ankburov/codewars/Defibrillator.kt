package io.ankburov.codewars

import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val userLongitude = input.next().parseDouble()
    val userLatitude = input.next().parseDouble()
    val N = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }
    val defibrillators = (0 until N).map {
        val splittedData = input.nextLine().split(";")
        Defibrillator(splittedData[1], splittedData[4].parseDouble(), splittedData[5].parseDouble())
    }

    println(defibrillators.sortedBy { getDistance(userLongitude, userLatitude, it.longitude, it.latitude) }
            .firstOrNull()
            ?.name)
}

private fun getDistance(userLongitude: Double, userLatitude: Double, pointLongitude: Double, pointLatitude: Double): Double {
    val x = (pointLongitude - userLongitude) * Math.cos((userLatitude + pointLatitude) / 2)
    val y = pointLatitude - userLatitude
    return Math.sqrt(x.sqr() + y.sqr()) * 6371
}

private fun String.parseDouble() = this.replace(",", ".").toDouble()

private fun Double.sqr() = Math.pow(this, 2.0)

class Defibrillator(val name: String, val longitude: Double, val latitude: Double)