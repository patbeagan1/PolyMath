package dev.patbeagan.lib.graph.util

import dev.patbeagan.lib.graph.domain.*
import dev.patbeagan.lib.graph.implementation.*

/**
 * Convenience functions for creating graphs with string-based vertices.
 * These functions automatically convert strings to SimpleVertex instances.
 */

/**
 * Creates a new directed graph with string-based vertices.
 */
fun directedGraph(
    block: MutableDirectedGraph<SimpleVertex, SimpleEdge<SimpleVertex>>.() -> Unit = {}
): MutableDirectedGraph<SimpleVertex, SimpleEdge<SimpleVertex>> {
    return MutableDirectedGraph { source, dest -> SimpleEdge(source, dest) }.apply(block)
}

/**
 * Creates a new undirected graph with string-based vertices.
 */
fun undirectedGraph(
    block: MutableUndirectedGraph<SimpleVertex, SimpleEdge<SimpleVertex>>.() -> Unit = {}
): MutableUndirectedGraph<SimpleVertex, SimpleEdge<SimpleVertex>> {
    return MutableUndirectedGraph { source, dest -> SimpleEdge(source, dest) }.apply(block)
}

/**
 * Creates a new weighted directed graph with string-based vertices.
 */
fun weightedDirectedGraph(
    defaultWeight: Double = 1.0,
    block: MutableWeightedDirectedGraph<SimpleVertex>.() -> Unit = {}
): MutableWeightedDirectedGraph<SimpleVertex> {
    return MutableWeightedDirectedGraph<SimpleVertex>(defaultWeight).apply(block)
}

/**
 * Creates a new weighted undirected graph with string-based vertices.
 */
fun weightedUndirectedGraph(
    defaultWeight: Double = 1.0,
    block: MutableWeightedUndirectedGraph<SimpleVertex>.() -> Unit = {}
): MutableWeightedUndirectedGraph<SimpleVertex> {
    return MutableWeightedUndirectedGraph<SimpleVertex>(defaultWeight).apply(block)
}
