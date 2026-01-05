package dev.patbeagan.lib.graph.domain

/**
 * Base interface for all graph types.
 * A graph consists of vertices and edges connecting them.
 *
 * @param V The vertex type
 * @param E The edge type
 */
interface Graph<V : Vertex, E : Edge<V>> {
    /**
     * Returns all vertices in the graph.
     */
    val vertices: Set<V>

    /**
     * Returns all edges in the graph.
     */
    val edges: List<E>

    /**
     * Adds a vertex to the graph.
     * @return The added vertex
     */
    fun addVertex(vertex: V): V

    /**
     * Removes a vertex and all its incident edges from the graph.
     * @return The removed vertex, or null if it didn't exist
     */
    fun removeVertex(vertex: V): V?

    /**
     * Adds an edge to the graph.
     * Vertices are automatically added if they don't exist.
     * @return The added edge
     */
    fun addEdge(edge: E): E

    /**
     * Removes an edge from the graph.
     * @return The removed edge, or null if it didn't exist
     */
    fun removeEdge(edge: E): E?

    /**
     * Checks if a vertex exists in the graph.
     */
    fun containsVertex(vertex: V): Boolean = vertices.contains(vertex)

    /**
     * Checks if an edge exists in the graph.
     */
    fun containsEdge(edge: E): Boolean = edges.contains(edge)

    /**
     * Returns all neighbors of a given vertex.
     * For directed graphs, returns only outgoing neighbors.
     */
    fun neighborsOf(vertex: V): Set<V>

    /**
     * Returns the degree of a vertex (number of incident edges).
     * For directed graphs, returns the out-degree.
     */
    fun degreeOf(vertex: V): Int = neighborsOf(vertex).size

    /**
     * Returns all edges incident to a vertex.
     */
    fun edgesOf(vertex: V): List<E>

    /**
     * Checks if the graph is empty (has no vertices).
     */
    fun isEmpty(): Boolean = vertices.isEmpty()

    /**
     * Returns the number of vertices in the graph.
     */
    fun vertexCount(): Int = vertices.size

    /**
     * Returns the number of edges in the graph.
     */
    fun edgeCount(): Int = edges.size
}

/**
 * Interface for directed graphs where edges have a direction.
 */
interface DirectedGraph<V : Vertex, E : Edge<V>> : Graph<V, E> {
    /**
     * Adds a directed edge from source to destination.
     * @return The created edge
     */
    fun addEdge(source: V, destination: V): E

    /**
     * Returns incoming neighbors of a vertex (for directed graphs).
     */
    fun inNeighborsOf(vertex: V): Set<V>

    /**
     * Returns outgoing neighbors of a vertex (for directed graphs).
     */
    fun outNeighborsOf(vertex: V): Set<V> = neighborsOf(vertex)

    /**
     * Returns the in-degree of a vertex (number of incoming edges).
     */
    fun inDegreeOf(vertex: V): Int = inNeighborsOf(vertex).size

    /**
     * Returns the out-degree of a vertex (number of outgoing edges).
     */
    fun outDegreeOf(vertex: V): Int = outNeighborsOf(vertex).size
}

/**
 * Interface for undirected graphs where edges have no direction.
 */
interface UndirectedGraph<V : Vertex, E : Edge<V>> : Graph<V, E> {
    /**
     * Adds an undirected edge between two vertices.
     * @return The created edge
     */
    fun addEdge(source: V, destination: V): E
}

/**
 * Interface for weighted graphs where edges have weights.
 */
interface WeightedGraph<V : Vertex, E : WeightedEdge<V>> : Graph<V, E> {
    /**
     * Returns the weight of an edge, or null if the edge doesn't exist.
     */
    fun weightOf(edge: E): Double? = edge.weight

    /**
     * Returns the total weight of all edges in the graph.
     */
    fun totalWeight(): Double = edges.sumOf { it.weight }
}
