# PolyMath Graph Library

A comprehensive graph theory library for Kotlin Multiplatform, following domain-driven design principles.

## Features

### Foundation Types
- **Vertex**: Base interface for graph vertices with type-safe identifiers
- **Edge**: Base interface for graph edges connecting vertices
- **Graph**: Core graph interface with operations for vertices and edges
- **DirectedGraph**: Interface for directed graphs
- **UndirectedGraph**: Interface for undirected graphs
- **WeightedGraph**: Interface for weighted graphs

### Graph Implementations
- `MutableDirectedGraph`: Mutable directed graph implementation
- `MutableUndirectedGraph`: Mutable undirected graph implementation
- `MutableWeightedDirectedGraph`: Mutable weighted directed graph
- `MutableWeightedUndirectedGraph`: Mutable weighted undirected graph

### Algorithms (Extension Functions)

#### Traversal
- `bfs(start)`: Breadth-First Search traversal
- `dfs(start)`: Depth-First Search traversal
- `dfsIterative(start)`: Iterative DFS (avoids stack overflow)
- `hasPath(source, destination)`: Check if path exists
- `reachableFrom(start)`: Find all reachable vertices

#### Shortest Path
- `dijkstra(source)`: Dijkstra's algorithm (non-negative weights)
- `shortestPath(source, destination)`: Find shortest path between two vertices
- `floydWarshall()`: All-pairs shortest paths
- `bellmanFord(source)`: Bellman-Ford algorithm (handles negative weights)

#### Minimum Spanning Tree
- `primMST(start?)`: Prim's algorithm for MST
- `kruskalMST()`: Kruskal's algorithm for MST
- `isConnected()`: Check if graph is connected
- `connectedComponents()`: Find all connected components

#### Connectivity
- `stronglyConnectedComponents()`: Kosaraju's algorithm for SCCs
- `stronglyConnectedComponentsTarjan()`: Tarjan's algorithm for SCCs
- `isStronglyConnected()`: Check if directed graph is strongly connected
- `isBipartite()`: Check if graph is bipartite
- `bipartitePartition()`: Get bipartite partition if exists

#### Topological Sort
- `topologicalSort()`: Kahn's algorithm for topological sorting
- `isAcyclic()`: Check if directed graph is acyclic
- `hasCycle()`: Check if directed graph has cycles

## Usage Examples

### Creating Graphs

```kotlin
import dev.patbeagan.lib.graph.*
import dev.patbeagan.lib.graph.algorithms.*
import dev.patbeagan.lib.graph.util.*

// Create a directed graph
val graph = directedGraph {
    val a = vertex("A")
    val b = vertex("B")
    val c = vertex("C")
    addEdge(a, b)
    addEdge(b, c)
}

// Create a weighted directed graph
val weightedGraph = weightedDirectedGraph {
    val a = vertex("A")
    val b = vertex("B")
    val c = vertex("C")
    addEdge(a, b, 1.0)
    addEdge(b, c, 2.0)
}
```

### Graph Traversal

```kotlin
// BFS traversal
val result = graph.bfs(vertex("A"))
when (result) {
    is TraversalResult.Success -> {
        println("Visited: ${result.visited}")
    }
    is TraversalResult.Failure -> {
        println("Error: ${result.reason}")
    }
}

// Check if path exists
if (graph.hasPath(vertex("A"), vertex("C"))) {
    println("Path exists!")
}
```

### Shortest Path

```kotlin
// Dijkstra's algorithm
val distances = weightedGraph.dijkstra(vertex("A"))
distances.forEach { (vertex, distance) ->
    println("Distance to $vertex: $distance")
}

// Find shortest path
val pathResult = weightedGraph.shortestPath(vertex("A"), vertex("C"))
when (pathResult) {
    is ShortestPathResult.Path -> {
        println("Path: ${pathResult.path}")
        println("Distance: ${pathResult.distance}")
    }
    is ShortestPathResult.NoPath -> {
        println("No path exists")
    }
}
```

### Minimum Spanning Tree

```kotlin
val mst = weightedGraph.primMST()
println("MST edges: ${mst.edges}")
println("Total weight: ${mst.totalWeight}")
```

### Topological Sort

```kotlin
val sortResult = graph.topologicalSort()
when (sortResult) {
    is TopologicalSortResult.Success -> {
        println("Topological order: ${sortResult.sorted}")
    }
    is TopologicalSortResult.CycleDetected -> {
        println("Cycle detected: ${sortResult.cycle}")
    }
}
```

### Connectivity

```kotlin
// Check if bipartite
if (graph.isBipartite()) {
    val (partition1, partition2) = graph.bipartitePartition()!!
    println("Partition 1: $partition1")
    println("Partition 2: $partition2")
}

// Find strongly connected components
val sccs = graph.stronglyConnectedComponents()
sccs.forEach { component ->
    println("SCC: $component")
}
```

## Architecture

The library follows Domain-Driven Design principles:

- **Domain Layer** (`domain/`): Core abstractions (Vertex, Edge, Graph interfaces)
- **Implementation Layer** (`implementation/`): Concrete graph implementations
- **Algorithms Layer** (`algorithms/`): Graph algorithms as extension functions
- **Utility Layer** (`util/`): Builder functions and convenience utilities

Algorithms are implemented as extension functions on graph types, similar to Kotlin's standard library list functions, providing a fluent and intuitive API.

## Multiplatform Support

The library is built for Kotlin Multiplatform and supports:
- JVM
- JavaScript (Node.js)
- Native (Linux x64)

## License

Part of the PolyMath project.
