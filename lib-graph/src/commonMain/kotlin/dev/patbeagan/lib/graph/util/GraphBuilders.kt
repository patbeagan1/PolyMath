package dev.patbeagan.lib.graph.util

import dev.patbeagan.lib.graph.domain.*
import dev.patbeagan.lib.graph.implementation.*

/**
 * Creates a new directed graph with the given configuration.
 * 
 * Example:
 * ```
 * val graph = directedGraph<String> {
 *     addEdge(vertex("A"), vertex("B"))
 *     addEdge(vertex("B"), vertex("C"))
 * }
 * ```
 */
fun <V : Vertex> directedGraph(
    block: MutableDirectedGraph<V, SimpleEdge<V>>.() -> Unit = {}
): MutableDirectedGraph<V, SimpleEdge<V>> {
    return MutableDirectedGraph { source, dest -> SimpleEdge(source, dest) }.apply(block)
}

/**
 * Creates a new undirected graph with the given configuration.
 * 
 * Example:
 * ```
 * val graph = undirectedGraph<String> {
 *     addEdge(vertex("A"), vertex("B"))
 *     addEdge(vertex("B"), vertex("C"))
 * }
 * ```
 */
fun <V : Vertex> undirectedGraph(
    block: MutableUndirectedGraph<V, SimpleEdge<V>>.() -> Unit = {}
): MutableUndirectedGraph<V, SimpleEdge<V>> {
    return MutableUndirectedGraph { source, dest -> SimpleEdge(source, dest) }.apply(block)
}

/**
 * Creates a new weighted directed graph with the given configuration.
 * 
 * Example:
 * ```
 * val graph = weightedDirectedGraph<String> {
 *     addEdge(vertex("A"), vertex("B"), 1.0)
 *     addEdge(vertex("B"), vertex("C"), 2.0)
 * }
 * ```
 */
fun <V : Vertex> weightedDirectedGraph(
    defaultWeight: Double = 1.0,
    block: MutableWeightedDirectedGraph<V>.() -> Unit = {}
): MutableWeightedDirectedGraph<V> {
    return MutableWeightedDirectedGraph<V>(defaultWeight).apply(block)
}

/**
 * Creates a new weighted undirected graph with the given configuration.
 * 
 * Example:
 * ```
 * val graph = weightedUndirectedGraph<String> {
 *     addEdge(vertex("A"), vertex("B"), 1.0)
 *     addEdge(vertex("B"), vertex("C"), 2.0)
 * }
 * ```
 */
fun <V : Vertex> weightedUndirectedGraph(
    defaultWeight: Double = 1.0,
    block: MutableWeightedUndirectedGraph<V>.() -> Unit = {}
): MutableWeightedUndirectedGraph<V> {
    return MutableWeightedUndirectedGraph<V>(defaultWeight).apply(block)
}
