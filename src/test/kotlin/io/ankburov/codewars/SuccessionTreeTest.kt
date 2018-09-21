package io.ankburov.codewars

import org.junit.Test
import java.util.*

class SuccessionTreeTest {


    @Test
    fun daf() {
        val senna = Person(name = "SennaLewis", parent = "LadyDavinaLewis", birthYear = 2010, deathYear = 0, religion = "Anglican", gender = 0)
        val tane = Person(name = "TaneLewis", parent = "LadyDavinaLewis", birthYear = 2012, deathYear = 0, religion = "Anglican", gender = 1)

        val persons = mutableListOf(senna, tane)

        persons.sortWith(compareBy({ it.gender }, { it.birthYear }))

        println()
        //            node.children.sortedWith(compareBy({ it.value.gender }, { it.value.birthYear })).reversed()

        /*Person(name=SennaLewis, parent=LadyDavinaLewis, birthYear=2010, deathYear=0, religion=Anglican, gender=0)
M
Person(name=TaneLewis, parent=LadyDavinaLewis, birthYear=2012, deathYear=0, religion=Anglican, gender=1)
*/
    }

    @Test
    fun test() {
        val input = Scanner(System.`in`)
        val n = input.nextInt()
        val successionTree = SuccessionTree()
        for (i in 0 until n) {
            val name = input.next()
            val parent = input.next()
            val birth = input.nextInt()
            val death = input.next()
            val religion = input.next()
            val gender = input.next()

            val person = Person(name, if (parent == "-") null else parent, birth,
                    if (death == "-") 0 else death.toInt(), religion, if (gender == "M") 1 else 0)
            successionTree.addPerson(person)
        }
    }

    /*val input = Scanner(System.`in`)
    val n = input.nextInt()
    for (i in 0 until n) {
        val name = input.next()
        val parent = input.next()
        val birth = input.nextInt()
        val death = input.next()
        val religion = input.next()
        val gender = input.next()
    }*/
}