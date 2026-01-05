package dev.patbeagan.lib.graph.algorithms

import dev.patbeagan.lib.graph.domain.*

/**
 * Finds strongly connected components using Kosaraju's algorithm.
 * 
 * Strongly connected components are maximal sets of vertices where
 * every vertex is reachable from every other vertex in the set.
 * 
 * Prerequisites: Graph must be directed.
 * 
 * @return List of sets, where each set contains vertices in one strongly connected component
 * 
 * Example:
 * ```
 * val graph = MutableDirectedGraph<String> { s, d -> SimpleEdge(s, d) }
 * graph.addEdge("A", "B")
 * graph.addEdge("B", "A")
 * val sccs = graph.stronglyConnectedComponents()
 * ```
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.stronglyConnectedComponents(): List<Set<V>> {
    val visited = mutableSetOf<V>()
    val stack = mutableListOf<V>()
    val components = mutableListOf<Set<V>>()

    // First DFS pass: fill stack with vertices in finishing order
    fun dfs1(vertex: V) {
        visited.add(vertex)
        outNeighborsOf(vertex).forEach { neighbor ->
            if (neighbor !in visited) {
                dfs1(neighbor)
            }
        }
        stack.add(vertex)
    }

    vertices.forEach { vertex ->
        if (vertex !in visited) {
            dfs1(vertex)
        }
    }

    // Build reverse graph
    val reverseGraph = mutableMapOf<V, MutableSet<V>>()
    vertices.forEach { reverseGraph[it] = mutableSetOf() }
    edges.forEach { edge ->
        reverseGraph.getOrPut(edge.destination) { mutableSetOf() }.add(edge.source)
    }

    // Second DFS pass: process vertices in reverse order of finishing times
    visited.clear()

    fun dfs2(vertex: V, component: MutableSet<V>) {
        visited.add(vertex)
        component.add(vertex)
        reverseGraph[vertex]?.forEach { neighbor ->
            if (neighbor !in visited) {
                dfs2(neighbor, component)
            }
        }
    }

    stack.reversed().forEach { vertex ->
        if (vertex !in visited) {
            val component = mutableSetOf<V>()
            dfs2(vertex, component)
            components.add(component)
        }
    }

    return components
}

/**
 * Finds strongly connected components using Tarjan's algorithm.
 * 
 * Tarjan's algorithm uses a single DFS pass with a stack to find SCCs.
 * 
 * @return List of sets, where each set contains vertices in one strongly connected component
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.stronglyConnectedComponentsTarjan(): List<Set<V>> {
    val index = mutableMapOf<V, Int>()
    val lowlink = mutableMapOf<V, Int>()
    val onStack = mutableSetOf<V>()
    val stack = mutableListOf<V>()
    val components = mutableListOf<Set<V>>()
    var currentIndex = 0

    fun strongConnect(vertex: V) {
        index[vertex] = currentIndex
        lowlink[vertex] = currentIndex
        currentIndex++
        stack.add(vertex)
        onStack.add(vertex)

        outNeighborsOf(vertex).forEach { neighbor ->
            if (neighbor !in index) {
                strongConnect(neighbor)
                val neighborLowlink = lowlink[neighbor] ?: Int.MAX_VALUE
                val vertexLowlink = lowlink[vertex] ?: Int.MAX_VALUE
                lowlink[vertex] = minOf(vertexLowlink, neighborLowlink)
            } else if (neighbor in onStack) {
                val neighborIndex = index[neighbor] ?: Int.MAX_VALUE
                val vertexLowlink = lowlink[vertex] ?: Int.MAX_VALUE
                lowlink[vertex] = minOf(vertexLowlink, neighborIndex)
            }
        }

        val vertexLowlink = lowlink[vertex] ?: Int.MAX_VALUE
        val vertexIndex = index[vertex] ?: Int.MAX_VALUE
        
        if (vertexLowlink == vertexIndex) {
            val component = mutableSetOf<V>()
            var w: V
            do {
                w = stack.removeLast()
                onStack.remove(w)
                component.add(w)
            } while (w != vertex)
            components.add(component)
        }
    }

    vertices.forEach { vertex ->
        if (vertex !in index) {
            strongConnect(vertex)
        }
    }

    return components
}

/**
 * Checks if the graph is bipartite.
 * 
 * A graph is bipartite if its vertices can be divided into two sets
 * such that no two vertices in the same set are adjacent.
 * 
 * @return true if the graph is bipartite, false otherwise
 * 
 * Example:
 * ```
 * val graph = MutableUndirectedGraph<String> { s, d -> SimpleEdge(s, d) }
 * graph.addEdge("A", "B")
 * graph.addEdge("B", "C")
 * val isBipartite = graph.isBipartite()
 * ```
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.isBipartite(): Boolean {
    if (vertices.isEmpty()) return true

    val color = mutableMapOf<V, Int>()
    val queue = ArrayDeque<V>()

    vertices.forEach { start ->
        if (start !in color) {
            color[start] = 0
            queue.addLast(start)

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                val currentColor = color[current] ?: 0

                neighborsOf(current).forEach { neighbor ->
                    if (neighbor !in color) {
                        color[neighbor] = 1 - currentColor
                        queue.addLast(neighbor)
                    } else {
                        val neighborColor = color[neighbor] ?: 0
                        if (neighborColor == currentColor) {
                            return false // Not bipartite
                        }
                    }
                }
            }
        }
    }

    return true
}

/**
 * Finds the bipartite partition if the graph is bipartite.
 * 
 * @return Pair of sets representing the two partitions, or null if not bipartite
 */
fun <V : Vertex, E : Edge<V>> Graph<V, E>.bipartitePartition(): Pair<Set<V>, Set<V>>? {
    if (!isBipartite()) return null

    val color = mutableMapOf<V, Int>()
    val queue = ArrayDeque<V>()

    vertices.forEach { start ->
        if (start !in color) {
            color[start] = 0
            queue.addLast(start)

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                val currentColor = color[current] ?: 0

                neighborsOf(current).forEach { neighbor ->
                    if (neighbor !in color) {
                        color[neighbor] = 1 - currentColor
                        queue.addLast(neighbor)
                    }
                }
            }
        }
    }

    val partition1 = color.filter { it.value == 0 }.keys.toSet()
    val partition2 = color.filter { it.value == 1 }.keys.toSet()

    return partition1 to partition2
}

/**
 * Checks if the directed graph is strongly connected.
 * 
 * A directed graph is strongly connected if there is a path from
 * every vertex to every other vertex.
 * 
 * @return true if the graph is strongly connected, false otherwise
 */
fun <V : Vertex, E : Edge<V>> DirectedGraph<V, E>.isStronglyConnected(): Boolean {
    if (vertices.isEmpty()) return true
    if (vertices.size == 1) return true

    val start = vertices.first()
    
    // Check if all vertices are reachable from start
    val reachableFromStart = mutableSetOf<V>()
    fun dfs(vertex: V) {
        reachableFromStart.add(vertex)
        outNeighborsOf(vertex).forEach { neighbor ->
            if (neighbor !in reachableFromStart) {
                dfs(neighbor)
            }
        }
    }
    dfs(start)

    if (reachableFromStart.size != vertices.size) {
        return false
    }

    // Check if start is reachable from all vertices (reverse graph)
    val reverseReachable = mutableSetOf<V>()
    fun dfsReverse(vertex: V) {
        reverseReachable.add(vertex)
        inNeighborsOf(vertex).forEach { neighbor ->
            if (neighbor !in reverseReachable) {
                dfsReverse(neighbor)
            }
        }
    }
    dfsReverse(start)

    return reverseReachable.size == vertices.size
}
