package io.ankburov.codewars

class SuccessionTree(
        private var root: Node<Person>? = null
) {

    fun addPerson(person: Person) {
        if (root == null) {
            root = Node(person)
        } else {
            addPersonRecursive(root!!, person)
        }
    }

    private fun addPersonRecursive(node: Node<Person>, person: Person) {
        if (node.value.name == person.parent) {
            node.children.add(Node(person, node))
            // sort
            node.children.sortWith(compareBy({ it.value.gender }, { it.value.birthYear }))
        } else {
            node.children.forEach { addPersonRecursive(it, person) }
        }
    }

    fun printTree() {
        printTreeRecursive(root!!)
    }

    private fun printTreeRecursive(node: Node<Person>) {
        with(node.value) {
            if (birthYear > deathYear && !religion.equals("catholic", ignoreCase = true)) {
                println(node.value.name)
            }
        }
        node.children.forEach(this::printTreeRecursive)
    }

    class Node<T : Person>(val value: T,
                           val parent: Node<T>? = null,
                           val children: MutableList<Node<T>> = arrayListOf())
}

class Person(
        val name: String,
        val parent: String?,
        val birthYear: Int,
        val deathYear: Int,
        val religion: String,
        val gender: Int // 0 is man, 1 is not man
)