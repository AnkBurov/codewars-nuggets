package io.ankburov.codewars

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val N = input.nextInt() // the total number of nodes in the level, including the gateways
    val L = input.nextInt() // the number of links
    val E = input.nextInt() // the number of exit gateways

    val graph = (0 until L).fold(Graph()) { graph, _ ->
        val firstNode = graph.addNodeIfNotExists(Node(input.nextInt()))
        val secondNode = graph.addNodeIfNotExists(Node(input.nextInt()))
        graph.apply { addEdge(Edge(firstNode, secondNode)) }
    }

    val gateways = (0 until E).map { Node(input.nextInt()) }

    // game loop
    while (true) {
        val skyNetAgentNode = Node(input.nextInt()) // The index of the node on which the Skynet agent is positioned this turn

        // Example: 0 1 are the indices of the nodes you wish to sever the link between
        val edgeToCut = graph.getLastEdgeOnShortestPathToNode(skyNetAgentNode, gateways)
        edgeToCut?.let(graph::removeEdge)
        println(edgeToCut?.toString())
    }
}

data class Node(val index: Int)

data class Edge(val first: Node, val second: Node, val weight: Int = 1) {
    override fun toString() = "${first.index} ${second.index}"
}

fun Node.other(edge: Edge) = when {
    this == edge.first -> edge.second
    this == edge.second -> edge.first
    else -> throw IllegalStateException("Node doesn't have connection with the edge")
}

data class FrontierElement(
        val node: Node,
        val costSoFar: Int = 0,
        val prioritySoFar: Int = 0,
        val cameFrom: Edge? = null,
        val previousElement: FrontierElement? = null
) {
    fun getFirstCameFrom(): Edge? {
        var currentFrontierElement: FrontierElement? = this
        var currentCameFrom: Edge? = null
        while (currentFrontierElement != null) {
            currentCameFrom = currentFrontierElement.cameFrom
            currentFrontierElement = currentFrontierElement.previousElement
        }
        return currentCameFrom
    }
}

class Graph {

    internal val nodes: MutableMap<Node, MutableSet<Edge>> = hashMapOf()

    fun addNodeIfNotExists(node: Node): Node {
        if (!nodes.containsKey(node)) {
            addNode(node)
        }
        return node
    }

    fun addNode(node: Node) {
        require(!nodes.containsKey(node))

        nodes.putIfAbsent(node, hashSetOf())
    }

    fun addEdge(edge: Edge) {
        require(nodes.containsKey(edge.first) && nodes.containsKey(edge.second))

        nodes.computeIfPresent(edge.first) { _, edges -> edges.apply { add(edge) } }
        nodes.computeIfPresent(edge.second) { _, edges -> edges.apply { add(edge) } }
    }

    fun removeEdge(edge: Edge) {
        nodes.forEach { _, edges -> edges.remove(edge) }
    }

    private fun Node.getNumberOfConnectedGoals(goals: List<Node>): Int {
        return nodes[this]
                ?.map(this::other)
                ?.fold(0) { count, otherNode -> if (goals.contains(otherNode)) count + 1 else count } ?: 0
    }

    fun getLastEdgeOnShortestPathToNode(start: Node, goals: List<Node>): Edge? {
        val startElement = FrontierElement(start)
        val frontier = LinkedBlockingQueue<FrontierElement>()
        frontier.add(startElement)

        val visited = mutableSetOf<Edge>()

        val goalFrontierElements = mutableListOf<FrontierElement>()
        while (frontier.isNotEmpty()) {
            val nextElement = frontier.remove()
            if (goals.contains(nextElement.node)) {
                goalFrontierElements.add(nextElement)
            }

            for (edge in nodes[nextElement.node]!!) {
                if (!visited.contains(edge)) {
                    val adjacentNode = nextElement.node.other(edge)
                    val numberOfConnectedGoals = adjacentNode.getNumberOfConnectedGoals(goals)
                    val adjacentFrontierElement = FrontierElement(adjacentNode, nextElement.costSoFar + 1,
                            nextElement.prioritySoFar + numberOfConnectedGoals,
                            cameFrom = edge, previousElement = nextElement)

                    frontier.add(adjacentFrontierElement)
                    visited.add(edge)
                }
            }
        }

        val minimumAvailableCost = goalFrontierElements.minBy(FrontierElement::costSoFar)?.costSoFar ?: 0

        val shortestGoalFrontierElement = goalFrontierElements.asSequence()
                .filter { it.costSoFar == minimumAvailableCost }
                .sortedByDescending { it.prioritySoFar }
                .firstOrNull()

        return if (minimumAvailableCost < 2) {
            shortestGoalFrontierElement?.cameFrom
        } else {
            val maxPriority = goalFrontierElements.maxBy { it.prioritySoFar }?.prioritySoFar ?: 0

            var sequence = goalFrontierElements.asSequence()
            if (maxPriority >= 2) {
                sequence = sequence
                        .filter { it.prioritySoFar >= 2 } // cut the most dangerous ones if exist
            }
            sequence // filter starting with the same edge as the shortest goal element
                    .filter { it.getFirstCameFrom() == shortestGoalFrontierElement?.getFirstCameFrom() }
                    .sortedBy { it.costSoFar }
                    .firstOrNull()
                    ?.cameFrom
        }
    }
}