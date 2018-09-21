package io.ankburov.codewars

import org.junit.Test

class TorTest {

    @Test
    fun test() {
        val moveQueue = getMoveQueue(5, 5, 10, 10)
//        val moveQueue = getMoveQueue(5, 5, 5, 7)
//        val moveQueue = getMoveQueue(31, 17, 31, 4)
//        val moveQueue = getMoveQueue(31, 17, 32, 25)
        //initialX 31, initialY 17, lightX 31, lightY 4
        println(moveQueue.toString())
        println(moveQueue.size)
    }
}