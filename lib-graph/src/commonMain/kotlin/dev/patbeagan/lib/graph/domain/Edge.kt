package dev.patbeagan.lib.graph.domain

/**
 * Represents an edge in a graph connecting two vertices.
 * This is the base interface for all edge types.
 */
interface Edge<V : Vertex> {
    /**
     * The source vertex of this edge.
     */
    val source: V

    /**
     * The destination vertex of this edge.
     */
    val destination: V
}

/**
 * Represents a weighted edge with an associated weight value.
 */
interface WeightedEdge<V : Vertex> : Edge<V> {
    /**
     * The weight of this edge.
     * Default weight is 1.0 for unweighted graphs.
     */
    val weight: Double
}

/**
 * Default implementation of a simple edge.
 */
data class SimpleEdge<V : Vertex>(
    override val source: V,
    override val destination: V
) : Edge<V> {
    override fun toString(): String = "$source -> $destination"
}

/**
 * Default implementation of a weighted edge.
 */
data class WeightedEdgeImpl<V : Vertex>(
    override val source: V,
    override val destination: V,
    override val weight: Double = 1.0
) : WeightedEdge<V> {
    override fun toString(): String = "$source -[$weight]-> $destination"
}

/**
 * Creates a simple edge between two vertices.
 */
fun <V : Vertex> edge(source: V, destination: V): SimpleEdge<V> =
    SimpleEdge(source, destination)

/**
 * Creates a weighted edge between two vertices.
 */
fun <V : Vertex> edge(source: V, destination: V, weight: Double): WeightedEdgeImpl<V> =
    WeightedEdgeImpl(source, destination, weight)
