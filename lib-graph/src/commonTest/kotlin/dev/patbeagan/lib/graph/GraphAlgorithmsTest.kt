package dev.patbeagan.lib.graph

import dev.patbeagan.lib.graph.algorithms.*
import dev.patbeagan.lib.graph.domain.*
import dev.patbeagan.lib.graph.implementation.*
import dev.patbeagan.lib.graph.util.*
import dev.patbeagan.lib.graph.util.directedGraph
import dev.patbeagan.lib.graph.util.undirectedGraph
import dev.patbeagan.lib.graph.util.weightedDirectedGraph
import dev.patbeagan.lib.graph.util.weightedUndirectedGraph
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GraphAlgorithmsTest {
    @Test
    fun testBFS() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
        }
        
        val result = graph.bfs(vertex("A"))
        assertTrue(result is TraversalResult.Success)
        val success = result as TraversalResult.Success
        assertEquals(3, success.visited.size)
        assertEquals(vertex("A"), success.visited[0])
    }

    @Test
    fun testDFS() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
        }
        
        val result = graph.dfs(vertex("A"))
        assertTrue(result is TraversalResult.Success)
        val success = result as TraversalResult.Success
        assertEquals(3, success.visited.size)
    }

    @Test
    fun testHasPath() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
        }
        
        assertTrue(graph.hasPath(vertex("A"), vertex("C")))
        assertFalse(graph.hasPath(vertex("C"), vertex("A")))
    }

    @Test
    fun testTopologicalSort() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
        }
        
        val result = graph.topologicalSort()
        assertTrue(result is TopologicalSortResult.Success)
        val success = result as TopologicalSortResult.Success
        assertEquals(3, success.sorted.size)
        assertTrue(success.sorted.indexOf(vertex("A")) < success.sorted.indexOf(vertex("B")))
        assertTrue(success.sorted.indexOf(vertex("B")) < success.sorted.indexOf(vertex("C")))
    }

    @Test
    fun testTopologicalSortWithCycle() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            addEdge(a, b)
            addEdge(b, a) // Creates a cycle
        }
        
        val result = graph.topologicalSort()
        assertTrue(result is TopologicalSortResult.CycleDetected)
    }

    @Test
    fun testDijkstra() {
        val graph = weightedDirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b, 1.0)
            addEdge(b, c, 2.0)
        }
        
        val distances = graph.dijkstra(vertex("A"))
        assertEquals(0.0, distances[vertex("A")])
        assertEquals(1.0, distances[vertex("B")])
        assertEquals(3.0, distances[vertex("C")])
    }

    @Test
    fun testShortestPath() {
        val graph = weightedDirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b, 1.0)
            addEdge(b, c, 2.0)
        }
        
        val result = graph.shortestPath(vertex("A"), vertex("C"))
        assertTrue(result is ShortestPathResult.Path)
        val path = result as ShortestPathResult.Path
        assertEquals(3.0, path.distance)
        assertEquals(3, path.path.size)
    }

    @Test
    fun testPrimMST() {
        val graph = weightedUndirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b, 1.0)
            addEdge(b, c, 2.0)
            addEdge(a, c, 5.0)
        }
        
        val mst = graph.primMST()
        assertEquals(2, mst.edges.size)
        assertEquals(3.0, mst.totalWeight)
    }

    @Test
    fun testKruskalMST() {
        val graph = weightedUndirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b, 1.0)
            addEdge(b, c, 2.0)
            addEdge(a, c, 5.0)
        }
        
        val mst = graph.kruskalMST()
        assertEquals(2, mst.edges.size)
        assertEquals(3.0, mst.totalWeight)
    }

    @Test
    fun testIsConnected() {
        val graph = undirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
        }
        
        assertTrue(graph.isConnected())
    }

    @Test
    fun testIsNotConnected() {
        val graph = undirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            addVertex(a)
            addVertex(b)
            // No edges
        }
        
        assertFalse(graph.isConnected())
    }

    @Test
    fun testStronglyConnectedComponents() {
        val graph = directedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, a) // Strongly connected component
            addEdge(b, c)
        }
        
        val sccs = graph.stronglyConnectedComponents()
        assertTrue(sccs.size >= 1)
    }

    @Test
    fun testIsBipartite() {
        val graph = undirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            val d = vertex("D")
            addEdge(a, b)
            addEdge(b, c)
            addEdge(c, d)
        }
        
        assertTrue(graph.isBipartite())
    }

    @Test
    fun testIsNotBipartite() {
        val graph = undirectedGraph {
            val a = vertex("A")
            val b = vertex("B")
            val c = vertex("C")
            addEdge(a, b)
            addEdge(b, c)
            addEdge(c, a) // Creates odd cycle
        }
        
        assertFalse(graph.isBipartite())
    }
}
