package io.ankburov.codewars

import org.junit.Assert.assertEquals
import org.junit.Test

class SkyNetEp1Test {

    @Test
    fun testGraph() {
        val firstNode = Node(1)
        val secondNode = Node(2)
        val edge = Edge(firstNode, secondNode)

        val graph = Graph()

        graph.addNode(firstNode)
        graph.addNode(secondNode)
        graph.addEdge(edge)

        assertEquals(2, graph.nodes.size)
        assertEquals(edge, graph.nodes[firstNode]?.firstOrNull())
        assertEquals(edge, graph.nodes[secondNode]?.firstOrNull())
    }

    @Test
    fun getLastEdgeOnShortestPathToNode() {
        val graph = Graph()
        for (i in 1..5) {
            graph.addNode(Node(i))
        }
        for (i in 1 until 5) {
            graph.addEdge(Edge(Node(i), Node(i + 1)))
        }
        val shortPathEdge = Edge(Node(1), Node(5))
        graph.addEdge(shortPathEdge)

        assertEquals(shortPathEdge, graph.getLastEdgeOnShortestPathToNode(Node(1), listOf(Node(5))))
    }

    @Test
    fun testTrickyOne() {
        val graph = Graph()
        for (i in 0..5) {
            graph.addNode(Node(i))
        }
        graph.addEdge(Edge(Node(0), Node(4)))
        graph.addEdge(Edge(Node(4), Node(5)))
        graph.addEdge(Edge(Node(4), Node(2)))
        graph.addEdge(Edge(Node(0), Node(1)))
        graph.addEdge(Edge(Node(0), Node(1)))
        graph.addEdge(Edge(Node(1), Node(2)))
        graph.addEdge(Edge(Node(1), Node(3))) //first move

        val result = graph.getLastEdgeOnShortestPathToNode(Node(0), listOf(Node(2), Node(3), Node(5)))
        println(result)
    }
}