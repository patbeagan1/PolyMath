package dev.patbeagan.lib.graph.algorithms

import dev.patbeagan.lib.graph.domain.*
import kotlin.math.min

/**
 * Result of a shortest path computation.
 */
sealed class ShortestPathResult<V : Vertex> {
    /**
     * Path found successfully.
     */
    data class Path<V : Vertex>(
        val source: V,
        val destination: V,
        val path: List<V>,
        val distance: Double
    ) : ShortestPathResult<V>()
    
    /**
     * No path exists between source and destination.
     */
    data class NoPath<V : Vertex>(
        val source: V,
        val destination: V
    ) : ShortestPathResult<V>()
    
    /**
     * Negative cycle detected (for Bellman-Ford).
     */
    data class NegativeCycle<V : Vertex>(
        val cycle: List<V>
    ) : ShortestPathResult<V>()
}

/**
 * Computes shortest paths from a source vertex to all other vertices using Dijkstra's algorithm.
 * 
 * Dijkstra's algorithm finds the shortest path from a source vertex to all other vertices
 * in a weighted graph with non-negative edge weights.
 * 
 * Prerequisites: All edge weights must be non-negative.
 * 
 * @param source The source vertex
 * @return Map of destination vertices to their shortest distances from source
 * 
 * Example:
 * ```
 * val graph = MutableWeightedDirectedGraph<String>()
 * graph.addEdge("A", "B", 1.0)
 * graph.addEdge("B", "C", 2.0)
 * val distances = graph.dijkstra(vertex("A"))
 * ```
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.dijkstra(source: V): Map<V, Double> {
    val distances = mutableMapOf<V, Double>()
    val visited = mutableSetOf<V>()
    
    // Initialize distances
    vertices.forEach { vertex ->
        distances[vertex] = if (vertex == source) 0.0 else Double.MAX_VALUE
    }

    // Priority queue simulation using a list (for multiplatform compatibility)
    val unvisited = vertices.toMutableSet()

    while (unvisited.isNotEmpty()) {
        // Find vertex with minimum distance
        val current = unvisited.minByOrNull { distances[it] ?: Double.MAX_VALUE }
            ?: break

        if (distances[current] == Double.MAX_VALUE) {
            break // No more reachable vertices
        }

        unvisited.remove(current)
        visited.add(current)

        // Update distances to neighbors
        edgesOf(current).forEach { edge ->
            val neighbor = edge.destination
            if (neighbor !in visited) {
                val currentDist = distances[current] ?: Double.MAX_VALUE
                val edgeWeight = edge.weight
                val newDist = currentDist + edgeWeight
                
                val neighborDist = distances[neighbor] ?: Double.MAX_VALUE
                if (newDist < neighborDist) {
                    distances[neighbor] = newDist
                }
            }
        }
    }

    return distances.filter { it.value != Double.MAX_VALUE }
}

/**
 * Finds the shortest path from source to destination using Dijkstra's algorithm.
 * 
 * @param source The source vertex
 * @param destination The destination vertex
 * @return ShortestPathResult containing the path and distance, or NoPath if unreachable
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.shortestPath(
    source: V,
    destination: V
): ShortestPathResult<V> {
    if (!containsVertex(source) || !containsVertex(destination)) {
        return ShortestPathResult.NoPath(source, destination)
    }

    val distances = mutableMapOf<V, Double>()
    val previous = mutableMapOf<V, V?>()
    val visited = mutableSetOf<V>()
    
    vertices.forEach { vertex ->
        distances[vertex] = if (vertex == source) 0.0 else Double.MAX_VALUE
        previous[vertex] = null
    }

    val unvisited = vertices.toMutableSet()

    while (unvisited.isNotEmpty()) {
        val current = unvisited.minByOrNull { distances[it] ?: Double.MAX_VALUE }
            ?: break

        if (distances[current] == Double.MAX_VALUE) {
            break
        }

        if (current == destination) {
            // Reconstruct path
            val path = mutableListOf<V>()
            var node: V? = destination
            while (node != null) {
                path.add(0, node)
                node = previous[node]
            }
            
            val distance = distances[destination] ?: Double.MAX_VALUE
            return ShortestPathResult.Path(source, destination, path, distance)
        }

        unvisited.remove(current)
        visited.add(current)

        edgesOf(current).forEach { edge ->
            val neighbor = edge.destination
            if (neighbor !in visited) {
                val currentDist = distances[current] ?: Double.MAX_VALUE
                val newDist = currentDist + edge.weight
                
                val neighborDist = distances[neighbor] ?: Double.MAX_VALUE
                if (newDist < neighborDist) {
                    distances[neighbor] = newDist
                    previous[neighbor] = current
                }
            }
        }
    }

    return ShortestPathResult.NoPath(source, destination)
}

/**
 * Computes shortest paths between all pairs of vertices using Floyd-Warshall algorithm.
 * 
 * Floyd-Warshall finds shortest paths between all pairs of vertices in a weighted graph.
 * It can handle negative edge weights (but not negative cycles).
 * 
 * @return Map of (source, destination) pairs to their shortest distances
 * 
 * Example:
 * ```
 * val graph = MutableWeightedDirectedGraph<String>()
 * graph.addEdge("A", "B", 1.0)
 * graph.addEdge("B", "C", 2.0)
 * val allPaths = graph.floydWarshall()
 * ```
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.floydWarshall(): Map<Pair<V, V>, Double> {
    val distances = mutableMapOf<Pair<V, V>, Double>()
    
    // Initialize distances
    vertices.forEach { v1 ->
        vertices.forEach { v2 ->
            distances[v1 to v2] = if (v1 == v2) {
                0.0
            } else {
                // Find direct edge
                val edge = edges.find { it.source == v1 && it.destination == v2 }
                edge?.weight ?: Double.MAX_VALUE
            }
        }
    }

    // Floyd-Warshall algorithm
    vertices.forEach { k ->
        vertices.forEach { i ->
            vertices.forEach { j ->
                val distIK = distances[i to k] ?: Double.MAX_VALUE
                val distKJ = distances[k to j] ?: Double.MAX_VALUE
                val distIJ = distances[i to j] ?: Double.MAX_VALUE
                
                if (distIK != Double.MAX_VALUE && distKJ != Double.MAX_VALUE) {
                    val newDist = distIK + distKJ
                    if (newDist < distIJ) {
                        distances[i to j] = newDist
                    }
                }
            }
        }
    }

    return distances.filter { it.value != Double.MAX_VALUE }
}

/**
 * Computes shortest paths from a source vertex using Bellman-Ford algorithm.
 * 
 * Bellman-Ford can handle graphs with negative edge weights and detects negative cycles.
 * 
 * Prerequisites: None (can handle negative weights, but detects negative cycles).
 * 
 * @param source The source vertex
 * @return Map of vertices to their shortest distances, or null if negative cycle detected
 * 
 * Example:
 * ```
 * val graph = MutableWeightedDirectedGraph<String>()
 * graph.addEdge("A", "B", -1.0)
 * graph.addEdge("B", "C", 2.0)
 * val distances = graph.bellmanFord(vertex("A"))
 * ```
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.bellmanFord(source: V): Map<V, Double>? {
    val distances = mutableMapOf<V, Double>()
    
    vertices.forEach { vertex ->
        distances[vertex] = if (vertex == source) 0.0 else Double.MAX_VALUE
    }

    // Relax edges |V| - 1 times
    repeat(vertices.size - 1) {
        edges.forEach { edge ->
            val sourceDist = distances[edge.source] ?: Double.MAX_VALUE
            val destDist = distances[edge.destination] ?: Double.MAX_VALUE
            
            if (sourceDist != Double.MAX_VALUE) {
                val newDist = sourceDist + edge.weight
                if (newDist < destDist) {
                    distances[edge.destination] = newDist
                }
            }
        }
    }

    // Check for negative cycles
    edges.forEach { edge ->
        val sourceDist = distances[edge.source] ?: Double.MAX_VALUE
        val destDist = distances[edge.destination] ?: Double.MAX_VALUE
        
        if (sourceDist != Double.MAX_VALUE && sourceDist + edge.weight < destDist) {
            // Negative cycle detected
            return null
        }
    }

    return distances.filter { it.value != Double.MAX_VALUE }
}
