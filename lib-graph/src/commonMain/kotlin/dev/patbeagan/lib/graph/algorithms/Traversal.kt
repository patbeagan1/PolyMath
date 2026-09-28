package dev.patbeagan.lib.graph.algorithms

import dev.patbeagan.lib.graph.domain.*

/**
 * Result of a graph traversal operation.
 */
sealed class TraversalResult<V : Vertex> {
    /**
     * Successful traversal with visited vertices in order.
     */
    data class Success<V : Vertex>(val start: V, val visited: List<V>) : TraversalResult<V>()
    
    /**
     * Failed traversal (e.g., start vertex not found).
     */
    data class Failure<V : Vertex>(val reason: String) : TraversalResult<V>()
}

/**
 * Performs Breadth-First Search (BFS) traversal starting from the given vertex.
 * 
 * BFS explores all vertices at the current depth before moving to the next level.
 * 
 * @param start The starting vertex
 * @param onVisit Optional callback invoked when visiting each vertex. Return false to stop traversal.
 * @return TraversalResult containing the visited vertices in BFS order
 * 
 * Example:
 * ```
 * val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
 * graph.addEdge("A", "B")
 * graph.addEdge("A", "C")
 * val result = graph.bfs(vertex("A"))
 * ```
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.bfs(
    start: V,
    onVisit: (V) -> Boolean = { true }
): TraversalResult<V> {
    if (!containsVertex(start)) {
        return TraversalResult.Failure("Start vertex not found in graph")
    }

    val visited = mutableSetOf<V>()
    val queue = ArrayDeque<V>()
    val result = mutableListOf<V>()

    visited.add(start)
    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        
        if (!onVisit(current)) {
            break
        }
        
        result.add(current)

        neighborsOf(current).forEach { neighbor ->
            if (neighbor !in visited) {
                visited.add(neighbor)
                queue.addLast(neighbor)
            }
        }
    }

    return TraversalResult.Success(start, result)
}

/**
 * Performs Depth-First Search (DFS) traversal starting from the given vertex.
 * 
 * DFS explores as far as possible along each branch before backtracking.
 * 
 * @param start The starting vertex
 * @param onVisit Optional callback invoked when visiting each vertex. Return false to stop traversal.
 * @return TraversalResult containing the visited vertices in DFS order
 * 
 * Example:
 * ```
 * val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
 * graph.addEdge("A", "B")
 * graph.addEdge("B", "C")
 * val result = graph.dfs(vertex("A"))
 * ```
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.dfs(
    start: V,
    onVisit: (V) -> Boolean = { true }
): TraversalResult<V> {
    if (!containsVertex(start)) {
        return TraversalResult.Failure("Start vertex not found in graph")
    }

    val visited = mutableSetOf<V>()
    val result = mutableListOf<V>()

    fun dfsRecursive(vertex: V) {
        if (vertex in visited) return
        
        visited.add(vertex)
        
        if (!onVisit(vertex)) {
            return
        }
        
        result.add(vertex)

        neighborsOf(vertex).forEach { neighbor ->
            if (neighbor !in visited) {
                dfsRecursive(neighbor)
            }
        }
    }

    dfsRecursive(start)
    return TraversalResult.Success(start, result)
}

/**
 * Performs iterative Depth-First Search using a stack.
 * Useful for large graphs to avoid stack overflow.
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.dfsIterative(
    start: V,
    onVisit: (V) -> Boolean = { true }
): TraversalResult<V> {
    if (!containsVertex(start)) {
        return TraversalResult.Failure("Start vertex not found in graph")
    }

    val visited = mutableSetOf<V>()
    val stack = ArrayDeque<V>()
    val result = mutableListOf<V>()

    stack.addLast(start)

    while (stack.isNotEmpty()) {
        val current = stack.removeLast()
        
        if (current in visited) continue
        
        visited.add(current)
        
        if (!onVisit(current)) {
            break
        }
        
        result.add(current)

        // Add neighbors in reverse order to maintain DFS order
        neighborsOf(current).reversed().forEach { neighbor ->
            if (neighbor !in visited) {
                stack.addLast(neighbor)
            }
        }
    }

    return TraversalResult.Success(start, result)
}

/**
 * Checks if there is a path from source to destination using BFS.
 * 
 * @return true if a path exists, false otherwise
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.hasPath(
    source: V,
    destination: V
): Boolean {
    if (!containsVertex(source) || !containsVertex(destination)) {
        return false
    }
    
    if (source == destination) {
        return true
    }

    val visited = mutableSetOf<V>()
    val queue = ArrayDeque<V>()
    
    visited.add(source)
    queue.addLast(source)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        
        neighborsOf(current).forEach { neighbor ->
            if (neighbor == destination) {
                return true
            }
            
            if (neighbor !in visited) {
                visited.add(neighbor)
                queue.addLast(neighbor)
            }
        }
    }

    return false
}

/**
 * Finds all vertices reachable from the given start vertex.
 * 
 * @return Set of all reachable vertices including the start vertex
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.reachableFrom(start: V): Set<V> {
    val result = bfs(start)
    return when (result) {
        is TraversalResult.Success -> result.visited.toSet()
        is TraversalResult.Failure -> emptySet()
    }
}
