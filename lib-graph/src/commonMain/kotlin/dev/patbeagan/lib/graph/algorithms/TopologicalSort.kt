package dev.patbeagan.lib.graph.algorithms

import dev.patbeagan.lib.graph.domain.*

/**
 * Result of topological sorting operation.
 */
sealed class TopologicalSortResult<V : Vertex> {
    /**
     * Successful topological sort.
     */
    data class Success<V : Vertex>(val sorted: List<V>) : TopologicalSortResult<V>()
    
    /**
     * Failed due to cycles in the graph.
     */
    data class CycleDetected<V : Vertex>(val cycle: List<V>) : TopologicalSortResult<V>()
}

/**
 * Performs topological sort using Kahn's algorithm.
 * 
 * Topological sort orders vertices such that for every directed edge (u, v),
 * vertex u comes before vertex v in the ordering.
 * 
 * Prerequisites: Graph must be a directed acyclic graph (DAG).
 * 
 * @return TopologicalSortResult containing the sorted vertices or a detected cycle
 * 
 * Example:
 * ```
 * val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
 * graph.addEdge("A", "B")
 * graph.addEdge("B", "C")
 * val result = graph.topologicalSort()
 * ```
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.topologicalSort(): TopologicalSortResult<V> {
    val inDegree = mutableMapOf<V, Int>()
    val queue = ArrayDeque<V>()
    val result = mutableListOf<V>()

    // Initialize in-degrees
    vertices.forEach { vertex ->
        inDegree[vertex] = inDegreeOf(vertex)
        if (inDegree[vertex] == 0) {
            queue.addLast(vertex)
        }
    }

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        result.add(current)

        outNeighborsOf(current).forEach { neighbor ->
            val currentInDegree = inDegree[neighbor] ?: 0
            inDegree[neighbor] = currentInDegree - 1
            
            if (inDegree[neighbor] == 0) {
                queue.addLast(neighbor)
            }
        }
    }

    // If result size doesn't match vertex count, there's a cycle
    if (result.size != vertices.size) {
        // Find cycle using DFS
        val cycle = findCycle()
        return TopologicalSortResult.CycleDetected(cycle)
    }

    return TopologicalSortResult.Success(result)
}

/**
 * Finds a cycle in the directed graph using DFS.
 */
private fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.findCycle(): List<V> {
    val visited = mutableSetOf<V>()
    val recStack = mutableSetOf<V>()
    val path = mutableListOf<V>()

    fun dfs(vertex: V): List<V>? {
        if (vertex in recStack) {
            // Found a cycle
            val cycleStart = path.indexOf(vertex)
            return path.subList(cycleStart, path.size) + vertex
        }
        
        if (vertex in visited) {
            return null
        }

        visited.add(vertex)
        recStack.add(vertex)
        path.add(vertex)

        outNeighborsOf(vertex).forEach { neighbor ->
            val cycle = dfs(neighbor)
            if (cycle != null) {
                return cycle
            }
        }

        recStack.remove(vertex)
        path.removeLast()
        return null
    }

    vertices.forEach { vertex ->
        if (vertex !in visited) {
            val cycle = dfs(vertex)
            if (cycle != null) {
                return cycle
            }
        }
    }

    return emptyList()
}

/**
 * Checks if the directed graph is acyclic (has no cycles).
 * 
 * @return true if the graph is acyclic, false otherwise
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.isAcyclic(): Boolean {
    val result = topologicalSort()
    return result is TopologicalSortResult.Success
}

/**
 * Checks if the directed graph contains a cycle.
 * 
 * @return true if the graph contains a cycle, false otherwise
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.hasCycle(): Boolean {
    return !isAcyclic()
}
