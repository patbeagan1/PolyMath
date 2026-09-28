package dev.patbeagan.lib.graph.domain

/**
 * Represents a vertex (node) in a graph.
 * This is a type-safe identifier for graph vertices.
 */
interface Vertex {
    /**
     * Returns a unique identifier for this vertex.
     * Used for equality and hashing.
     */
    val id: String
}

/**
 * Default implementation of a vertex with a string identifier.
 */
data class SimpleVertex(override val id: String) : Vertex {
    override fun toString(): String = id
}

/**
 * Creates a simple vertex from a string identifier.
 */
fun vertex(id: String): SimpleVertex = SimpleVertex(id)
