package dev.patbeagan.lib.graph

import dev.patbeagan.lib.graph.domain.*
import dev.patbeagan.lib.graph.implementation.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GraphFoundationTest {
    @Test
    fun testVertexCreation() {
        val v1 = vertex("A")
        val v2 = vertex("B")
        
        assertEquals("A", v1.id)
        assertEquals("B", v2.id)
        assertTrue(v1 != v2)
    }

    @Test
    fun testEdgeCreation() {
        val v1 = vertex("A")
        val v2 = vertex("B")
        val e1 = edge(v1, v2)
        val e2 = edge(v1, v2, 5.0)
        
        assertEquals(v1, e1.source)
        assertEquals(v2, e1.destination)
        assertEquals(5.0, e2.weight)
    }

    @Test
    fun testDirectedGraphBasicOperations() {
        val graph = MutableDirectedGraph<SimpleVertex> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        val v3 = vertex("C")
        
        graph.addVertex(v1)
        graph.addVertex(v2)
        graph.addEdge(v1, v2)
        graph.addEdge(v2, v3)
        
        assertEquals(3, graph.vertexCount())
        assertEquals(2, graph.edgeCount())
        assertTrue(graph.containsVertex(v1))
        assertTrue(graph.containsEdge(SimpleEdge(v1, v2)))
        assertEquals(setOf(v2), graph.neighborsOf(v1))
        assertEquals(setOf(v3), graph.neighborsOf(v2))
    }

    @Test
    fun testUndirectedGraphBasicOperations() {
        val graph = MutableUndirectedGraph<SimpleVertex> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        
        graph.addEdge(v1, v2)
        
        assertEquals(2, graph.vertexCount())
        assertEquals(1, graph.edgeCount())
        assertTrue(graph.neighborsOf(v1).contains(v2))
        assertTrue(graph.neighborsOf(v2).contains(v1))
    }

    @Test
    fun testWeightedGraph() {
        val graph = MutableWeightedDirectedGraph<SimpleVertex>()
        val v1 = vertex("A")
        val v2 = vertex("B")
        
        graph.addEdge(v1, v2, 5.0)
        
        val edge = graph.edges.first()
        assertEquals(5.0, edge.weight)
        assertEquals(5.0, graph.totalWeight())
    }

    @Test
    fun testRemoveVertex() {
        val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        
        graph.addEdge(v1, v2)
        assertEquals(2, graph.vertexCount())
        assertEquals(1, graph.edgeCount())
        
        graph.removeVertex(v1)
        assertEquals(1, graph.vertexCount())
        assertEquals(0, graph.edgeCount())
    }

    @Test
    fun testRemoveEdge() {
        val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        
        val edge = graph.addEdge(v1, v2)
        assertEquals(1, graph.edgeCount())
        
        graph.removeEdge(edge)
        assertEquals(0, graph.edgeCount())
    }

    @Test
    fun testDegree() {
        val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        val v3 = vertex("C")
        
        graph.addEdge(v1, v2)
        graph.addEdge(v1, v3)
        
        assertEquals(2, graph.degreeOf(v1))
        assertEquals(0, graph.degreeOf(v2))
    }

    @Test
    fun testInDegree() {
        val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
        val v1 = vertex("A")
        val v2 = vertex("B")
        val v3 = vertex("C")
        
        graph.addEdge(v1, v2)
        graph.addEdge(v3, v2)
        
        assertEquals(0, graph.inDegreeOf(v1))
        assertEquals(2, graph.inDegreeOf(v2))
        assertEquals(0, graph.inDegreeOf(v3))
    }
}
