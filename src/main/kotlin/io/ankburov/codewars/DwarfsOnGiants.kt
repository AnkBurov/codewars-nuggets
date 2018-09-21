package io.ankburov.codewars

import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt() // the number of relationships of influence
    val tree = DwarfTree()
    for (i in 0 until n) {
        val x = input.nextInt() // a relationship of influence between two people (x influences y)
        val y = input.nextInt()
        tree.addRelation(x, y)
    }

    // The number of people involved in the longest succession of influences
    println(tree.getMaxDepth())
}

class DwarfTree(private var root: DwarfNode = DwarfNode(-1)) {
    fun addRelation(parentIndex: Int, childIndex: Int) {
        val parentNode = getNodeRecursive(root, parentIndex) ?: root.let { rootNode ->
            DwarfNode(parentIndex).apply { rootNode.children.add(this) }
        }
        val childNode = getNodeRecursive(root, childIndex) ?: DwarfNode(childIndex)
        parentNode.children.add(childNode)
    }

    fun getMaxDepth(currentNode: DwarfNode = root, currentDepth: Int = 0): Int {
        return currentNode.children
                .map { getMaxDepth(it, currentDepth + 1) }
                .max() ?: currentDepth
    }

    private fun getNodeRecursive(node: DwarfNode, index: Int): DwarfNode? {
        return when (index) {
            node.index -> node
            else -> node.children
                    .mapNotNull { getNodeRecursive(it, index) }
                    .firstOrNull()
        }
    }
}

class DwarfNode(val index: Int, val children: MutableList<DwarfNode> = mutableListOf())