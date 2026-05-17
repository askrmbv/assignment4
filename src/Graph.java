import java.util.*;

/**
 * Represents a directed graph using an adjacency list.
 * Supports adding vertices and edges, printing the graph structure,
 * and performing BFS and DFS traversals.
 */
public class Graph {

    // Maps each vertex ID to its Vertex object
    private Map<Integer, Vertex> vertices;

    // Adjacency list: maps each vertex ID to its list of neighbor IDs
    private Map<Integer, List<Integer>> adjList;

    /** Constructs an empty directed graph. */
    public Graph() {
        vertices = new HashMap<>();
        adjList  = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * If the vertex already exists, it is ignored.
     */
    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjList.put(v.getId(), new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge from vertex 'from' to vertex 'to'.
     * Both vertices must already exist in the graph.
     */
    public void addEdge(int from, int to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            System.out.println("Error: vertex " + from + " or " + to + " not found.");
            return;
        }
        adjList.get(from).add(to);
    }

    /**
     * Prints the adjacency list representation of the graph.
     * Shows each vertex and its outgoing neighbors.
     */
    public void printGraph() {
        System.out.println("Graph (Adjacency List):");
        List<Integer> sortedKeys = new ArrayList<>(adjList.keySet());
        Collections.sort(sortedKeys);
        for (int id : sortedKeys) {
            System.out.println("  " + id + " -> " + adjList.get(id));
        }
    }

    /**
     * Performs Breadth-First Search starting from the given vertex ID.
     * Uses a queue to visit vertices level by level.
     * Prints the traversal order.
     */
    public void bfs(int start) {
        if (!adjList.containsKey(start)) {
            System.out.println("BFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        // Enqueue the starting vertex
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            // Visit all unvisited neighbors
            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("BFS from " + start + ": " + order);
    }

    /**
     * Performs Depth-First Search starting from the given vertex ID.
     * Uses a stack (iterative) to explore as deep as possible before backtracking.
     * Prints the traversal order.
     */
    public void dfs(int start) {
        if (!adjList.containsKey(start)) {
            System.out.println("DFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> order = new ArrayList<>();

        // Push the starting vertex
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            // Process only if not yet visited
            if (!visited.contains(current)) {
                visited.add(current);
                order.add(current);

                // Push neighbors in reverse order to maintain left-to-right traversal
                List<Integer> neighbors = adjList.get(current);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    if (!visited.contains(neighbors.get(i))) {
                        stack.push(neighbors.get(i));
                    }
                }
            }
        }

        System.out.println("DFS from " + start + ": " + order);
    }

    /** Returns the number of vertices in the graph. */
    public int vertexCount() {
        return vertices.size();
    }

    /** Returns the total number of directed edges in the graph. */
    public int edgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}
