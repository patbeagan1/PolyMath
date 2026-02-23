package dev.patbeagan.lib.graph
<<<<<<< HEAD

/**
 * PolyMath Graph Library
 * 
 * A comprehensive graph theory library for Kotlin Multiplatform.
 * 
 * This library provides:
 * - Foundation types: Vertex, Edge, Graph interfaces
 * - Graph implementations: Directed, Undirected, Weighted graphs
 * - Graph algorithms as extension functions:
 *   - Traversal: BFS, DFS
 *   - Shortest Path: Dijkstra, Bellman-Ford, Floyd-Warshall
 *   - Minimum Spanning Tree: Prim, Kruskal
 *   - Connectivity: Strongly Connected Components, Bipartite checking
 *   - Topological Sort: Kahn's algorithm
 * 
 * Example usage:
 * ```
 * import dev.patbeagan.lib.graph.*
 * import dev.patbeagan.lib.graph.algorithms.*
 * 
 * val graph = weightedDirectedGraph<String> {
 *     addEdge(vertex("A"), vertex("B"), 1.0)
 *     addEdge(vertex("B"), vertex("C"), 2.0)
 * }
 * 
 * val distances = graph.dijkstra(vertex("A"))
 * val path = graph.shortestPath(vertex("A"), vertex("C"))
 * ```
 */
fun main() = println("PolyMath Graph Library")
=======
fun main() = println("hello world")
>>>>>>> b3d087a7a68cda89bfa9b5959029c058b75dc42f
