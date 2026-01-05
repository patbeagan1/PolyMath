package dev.patbeagan.lib.graph.implementation

import dev.patbeagan.lib.graph.domain.*

/**
 * Base implementation of a mutable graph.
 * This provides the core functionality for graph operations.
 */
abstract class MutableGraph<V : Vertex, E : Edge<V>> : Graph<V, E> {
    protected val _vertices: MutableSet<V> = mutableSetOf()
    protected val _edges: MutableList<E> = mutableListOf()
    protected val _adjacencyMap: MutableMap<V, MutableSet<V>> = mutableMapOf()
    protected val _edgeMap: MutableMap<V, MutableList<E>> = mutableMapOf()

    override val vertices: Set<V> get() = _vertices.toSet()
    override val edges: List<E> get() = _edges.toList()

    override fun addVertex(vertex: V): V {
        if (_vertices.add(vertex)) {
            _adjacencyMap[vertex] = mutableSetOf()
            _edgeMap[vertex] = mutableListOf()
        }
        return vertex
    }

    override fun removeVertex(vertex: V): V? {
        if (!_vertices.remove(vertex)) return null

        // Remove all edges incident to this vertex
        val edgesToRemove = _edges.filter { it.source == vertex || it.destination == vertex }
        edgesToRemove.forEach { _edges.remove(it) }

        // Update adjacency maps
        _adjacencyMap.remove(vertex)
        _edgeMap.remove(vertex)
        
        // Remove references from other vertices
        _adjacencyMap.values.forEach { it.remove(vertex) }
        _edgeMap.values.forEach { it.removeAll { edge -> edge.destination == vertex } }

        return vertex
    }

    override fun addEdge(edge: E): E {
        // Ensure vertices exist
        addVertex(edge.source)
        addVertex(edge.destination)

        if (!_edges.contains(edge)) {
            _edges.add(edge)
            _adjacencyMap[edge.source]?.add(edge.destination)
            _edgeMap[edge.source]?.add(edge)
        }

        return edge
    }

    override fun removeEdge(edge: E): E? {
        if (!_edges.remove(edge)) return null

        _adjacencyMap[edge.source]?.remove(edge.destination)
        _edgeMap[edge.source]?.remove(edge)

        return edge
    }

    override fun neighborsOf(vertex: V): Set<V> {
        return _adjacencyMap[vertex]?.toSet() ?: emptySet()
    }

    override fun edgesOf(vertex: V): List<E> {
        return _edgeMap[vertex]?.toList() ?: emptyList()
    }
}

/**
 * Implementation of a mutable directed graph.
 */
class MutableDirectedGraph<V : Vertex, E : Edge<V>>(
    private val edgeFactory: (V, V) -> E
) : MutableGraph<V, E>(), DirectedGraph<V, E> {
    
    private val _incomingEdges: MutableMap<V, MutableList<E>> = mutableMapOf()
    private val _incomingNeighbors: MutableMap<V, MutableSet<V>> = mutableMapOf()

    override fun addEdge(source: V, destination: V): E {
        val edge = edgeFactory(source, destination)
        addEdge(edge)
        
        // Track incoming edges for directed graphs
        _incomingEdges.getOrPut(destination) { mutableListOf() }.add(edge)
        _incomingNeighbors.getOrPut(destination) { mutableSetOf() }.add(source)
        
        return edge
    }

    override fun inNeighborsOf(vertex: V): Set<V> {
        return _incomingNeighbors[vertex]?.toSet() ?: emptySet()
    }

    override fun removeEdge(edge: E): E? {
        val removed = super.removeEdge(edge)
        if (removed != null) {
            _incomingEdges[edge.destination]?.remove(edge)
            _incomingNeighbors[edge.destination]?.remove(edge.source)
        }
        return removed
    }

    override fun removeVertex(vertex: V): V? {
        val removed = super.removeVertex(vertex)
        if (removed != null) {
            _incomingEdges.remove(vertex)
            _incomingNeighbors.remove(vertex)
        }
        return removed
    }
}

/**
 * Implementation of a mutable undirected graph.
 */
class MutableUndirectedGraph<V : Vertex, E : Edge<V>>(
    private val edgeFactory: (V, V) -> E
) : MutableGraph<V, E>(), UndirectedGraph<V, E> {
    
    override fun addEdge(source: V, destination: V): E {
        val edge = edgeFactory(source, destination)
        addEdge(edge)
        
        // For undirected graphs, add reverse adjacency
        _adjacencyMap.getOrPut(destination) { mutableSetOf() }.add(source)
        _edgeMap.getOrPut(destination) { mutableListOf() }.add(edge)
        
        return edge
    }

    override fun neighborsOf(vertex: V): Set<V> {
        return _adjacencyMap[vertex]?.toSet() ?: emptySet()
    }
}

/**
 * Implementation of a mutable weighted directed graph.
 */
class MutableWeightedDirectedGraph<V : Vertex>(
    private val defaultWeight: Double = 1.0
) : MutableDirectedGraph<V, WeightedEdge<V>>(
    edgeFactory = { source, dest -> WeightedEdgeImpl(source, dest, defaultWeight) }
), WeightedGraph<V, WeightedEdge<V>> {
    
    fun addEdge(source: V, destination: V, weight: Double): WeightedEdge<V> {
        val edge = WeightedEdgeImpl(source, destination, weight)
        addEdge(edge)
        return edge
    }
}

/**
 * Implementation of a mutable weighted undirected graph.
 */
class MutableWeightedUndirectedGraph<V : Vertex>(
    private val defaultWeight: Double = 1.0
) : MutableUndirectedGraph<V, WeightedEdge<V>>(
    edgeFactory = { source, dest -> WeightedEdgeImpl(source, dest, defaultWeight) }
), WeightedGraph<V, WeightedEdge<V>> {
    
    fun addEdge(source: V, destination: V, weight: Double): WeightedEdge<V> {
        val edge = WeightedEdgeImpl(source, destination, weight)
        addEdge(edge)
        return edge
    }
}
