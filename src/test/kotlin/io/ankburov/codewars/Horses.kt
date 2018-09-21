package io.ankburov.codewars

fun main(args: Array<String>) {

//    val horses = mutableListOf<Int>()

    val horses = listOf(5, 8, 9)

    var closestHorsesDifference = Int.MAX_VALUE
    var previousHorse = 0
    horses.asSequence()
            .sorted()
            .forEach { horsePower ->
                val horseDiff = Math.abs(horsePower - previousHorse)
                if (horseDiff < closestHorsesDifference) closestHorsesDifference = horseDiff
                previousHorse = horsePower
            }

    println(closestHorsesDifference)

    /*var closestHorsesDifference = 0
    horses.forEachIndexed { index, horsePower ->
        val min = horses.asSequence()
                .filterIndexed { otherIndex, _ -> index != otherIndex }
                .map { comparingHorsePower -> Math.abs(horsePower - comparingHorsePower) }
                .min()
        if (min != null) closestHorsesDifference = min
    }

    println(closestHorsesDifference)*/
}