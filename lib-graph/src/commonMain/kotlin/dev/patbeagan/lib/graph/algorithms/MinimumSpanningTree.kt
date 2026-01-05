package dev.patbeagan.lib.graph.algorithms

import dev.patbeagan.lib.graph.domain.*

/**
 * Result of Minimum Spanning Tree computation.
 */
data class MSTResult<V : Vertex>(
    val edges: List<WeightedEdge<V>>,
    val totalWeight: Double
)

/**
 * Computes the Minimum Spanning Tree using Prim's algorithm.
 * 
 * Prim's algorithm grows the MST by adding the minimum-weight edge
 * that connects a vertex in the MST to a vertex outside the MST.
 * 
 * Prerequisites: Graph must be connected and undirected.
 * 
 * @param start Optional starting vertex. If null, starts from an arbitrary vertex.
 * @return MSTResult containing the MST edges and total weight
 * 
 * Example:
 * ```
 * val graph = MutableWeightedUndirectedGraph<String>()
 * graph.addEdge("A", "B", 1.0)
 * graph.addEdge("B", "C", 2.0)
 * val mst = graph.primMST()
 * ```
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.primMST(start: V? = null): MSTResult<V> {
    if (vertices.isEmpty()) {
        return MSTResult(emptyList(), 0.0)
    }

    val mstEdges = mutableListOf<WeightedEdge<V>>()
    val inMST = mutableSetOf<V>()
    val key = mutableMapOf<V, Double>()
    val parent = mutableMapOf<V, V?>()

    // Initialize keys
    vertices.forEach { vertex ->
        key[vertex] = Double.MAX_VALUE
        parent[vertex] = null
    }

    val startVertex = start ?: vertices.first()
    key[startVertex] = 0.0

    val unvisited = vertices.toMutableSet()

    while (unvisited.isNotEmpty()) {
        // Find vertex with minimum key
        val u = unvisited.minByOrNull { key[it] ?: Double.MAX_VALUE }
            ?: break

        if (key[u] == Double.MAX_VALUE) {
            break // Graph is not connected
        }

        unvisited.remove(u)
        inMST.add(u)

        // Add edge to MST if it's not the starting vertex
        parent[u]?.let { p ->
            val edge = edges.find { 
                (it.source == p && it.destination == u) || 
                (it.source == u && it.destination == p)
            } as? WeightedEdge<V>
            edge?.let { mstEdges.add(it) }
        }

        // Update keys of adjacent vertices
        edgesOf(u).forEach { edge ->
            val v = if (edge.destination == u) edge.source else edge.destination
            val weight = edge.weight
            
            if (v !in inMST && weight < (key[v] ?: Double.MAX_VALUE)) {
                key[v] = weight
                parent[v] = u
            }
        }
    }

    val totalWeight = mstEdges.sumOf { it.weight }
    return MSTResult(mstEdges, totalWeight)
}

/**
 * Computes the Minimum Spanning Tree using Kruskal's algorithm.
 * 
 * Kruskal's algorithm builds the MST by sorting edges by weight and
 * adding them one by one, skipping edges that would create cycles.
 * 
 * Prerequisites: Graph must be connected and undirected.
 * 
 * @return MSTResult containing the MST edges and total weight
 * 
 * Example:
 * ```
 * val graph = MutableWeightedUndirectedGraph<String>()
 * graph.addEdge("A", "B", 1.0)
 * graph.addEdge("B", "C", 2.0)
 * val mst = graph.kruskalMST()
 * ```
 */
fun <V : Vertex> WeightedGraph<V, WeightedEdge<V>>.kruskalMST(): MSTResult<V> {
    if (vertices.isEmpty()) {
        return MSTResult(emptyList(), 0.0)
    }

    val mstEdges = mutableListOf<WeightedEdge<V>>()
    val parent = mutableMapOf<V, V>()
    val rank = mutableMapOf<V, Int>()

    // Initialize union-find data structure
    vertices.forEach { vertex ->
        parent[vertex] = vertex
        rank[vertex] = 0
    }

    // Sort edges by weight
    val sortedEdges = edges.sortedBy { it.weight }

    // Find function for union-find
    fun find(x: V): V {
        if (parent[x] != x) {
            parent[x] = find(parent[x]!!) // Path compression
        }
        return parent[x]!!
    }

    // Union function for union-find
    fun union(x: V, y: V) {
        val rootX = find(x)
        val rootY = find(y)

        if (rootX == rootY) return // Already in same set

        // Union by rank
        val rankX = rank[rootX] ?: 0
        val rankY = rank[rootY] ?: 0

        if (rankX < rankY) {
            parent[rootX] = rootY
        } else if (rankX > rankY) {
            parent[rootY] = rootX
        } else {
            parent[rootY] = rootX
            rank[rootX] = rankX + 1
        }
    }

    // Process edges in sorted order
    sortedEdges.forEach { edge ->
        val source = edge.source
        val dest = edge.destination

        if (find(source) != find(dest)) {
            mstEdges.add(edge)
            union(source, dest)
        }
    }

    val totalWeight = mstEdges.sumOf { it.weight }
    return MSTResult(mstEdges, totalWeight)
}

/**
 * Checks if the graph is connected.
 * 
 * A graph is connected if there is a path between every pair of vertices.
 * 
 * @return true if the graph is connected, false otherwise
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.isConnected(): Boolean {
    if (vertices.isEmpty()) return true
    if (vertices.size == 1) return true

    val start = vertices.first()
    val reachable = reachableFrom(start)
    return reachable.size == vertices.size
}

/**
 * Finds all connected components in the graph.
 * 
 * @return List of sets, where each set contains vertices in one connected component
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.connectedComponents(): List<Set<V>> {
    val components = mutableListOf<Set<V>>()
    val visited = mutableSetOf<V>()

    vertices.forEach { vertex ->
        if (vertex !in visited) {
            val component = reachableFrom(vertex)
            components.add(component)
            visited.addAll(component)
        }
    }

    return components
}
