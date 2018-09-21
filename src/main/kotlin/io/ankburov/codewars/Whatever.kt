package io.ankburov.codewars

import java.util.*
import java.io.*
import java.math.*

/**
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    // game loop
    while (true) {
        var toppestMountainIndex = -1
        var toppestMountainH = 0
        for (i in 0 until 8) {
            val mountainH = input.nextInt() // represents the height of one mountain.
            if (mountainH > toppestMountainH) {
                toppestMountainH = mountainH
                toppestMountainIndex = i
            }
        }

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");

        println(toppestMountainIndex) // The index of the mountain to fire on.
    }

    /*// game loop
    while (true) {
        val mountains = mutableListOf<Int>()

        for (i in 0 until 8) {
            val mountainH = input.nextInt() // represents the height of one mountain.
            mountains.add(mountainH)
        }
        val sortedMountains = mountains.sortedByDescending { it }

        System.err.println(mountains.toString())

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");

        val indexOfFirst = mountains.indexOfFirst { it == sortedMountains.firstOrNull() }
        if (indexOfFirst != -1) {
            println(indexOfFirst) // The index of the mountain to fire on.
        }
    }*/
}