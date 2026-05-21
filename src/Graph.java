import java.util.*;

/**
 * Represents a directed weighted graph using an adjacency list.
 * Supports adding vertices and edges, printing the graph structure,
 * performing BFS and DFS traversals, and running Dijkstra's algorithm.
 */
public class Graph {

    // Maps each vertex ID to its Vertex object
    private Map<Integer, Vertex> vertices;

    // Adjacency list for BFS/DFS: maps each vertex ID to its list of neighbor IDs
    private Map<Integer, List<Integer>> adjList;

    // Weighted adjacency list for Dijkstra: maps each vertex ID to its list of edges
    private Map<Integer, List<int[]>> weightedAdjList;

    /** Constructs an empty directed weighted graph. */
    public Graph() {
        vertices          = new HashMap<>();
        adjList           = new HashMap<>();
        weightedAdjList   = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * If the vertex already exists, it is ignored.
     */
    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjList.put(v.getId(), new ArrayList<>());
            weightedAdjList.put(v.getId(), new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge from vertex 'from' to vertex 'to' with the given weight.
     * Both vertices must already exist in the graph.
     */
    public void addEdge(int from, int to, int weight) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            System.out.println("Error: vertex " + from + " or " + to + " not found.");
            return;
        }
        adjList.get(from).add(to);
        weightedAdjList.get(from).add(new int[]{to, weight});
    }

    /**
     * Prints the adjacency list with edge weights.
     * Shows each vertex and its outgoing neighbors with their weights.
     */
    public void printGraph() {
        System.out.println("Graph (Weighted Adjacency List):");
        List<Integer> sortedKeys = new ArrayList<>(weightedAdjList.keySet());
        Collections.sort(sortedKeys);
        for (int id : sortedKeys) {
            StringBuilder sb = new StringBuilder("  " + id + " -> ");
            List<int[]> edges = weightedAdjList.get(id);
            if (edges.isEmpty()) {
                sb.append("[]");
            } else {
                sb.append("[");
                for (int i = 0; i < edges.size(); i++) {
                    sb.append(edges.get(i)[0]).append("(w=").append(edges.get(i)[1]).append(")");
                    if (i < edges.size() - 1) sb.append(", ");
                }
                sb.append("]");
            }
            System.out.println(sb);
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
        List<Integer> order  = new ArrayList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

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
     * Uses a stack to explore as deep as possible before backtracking.
     * Prints the traversal order.
     */
    public void dfs(int start) {
        if (!adjList.containsKey(start)) {
            System.out.println("DFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited  = new HashSet<>();
        Deque<Integer> stack  = new ArrayDeque<>();
        List<Integer> order   = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                order.add(current);

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

    /**
     * Implements Dijkstra's algorithm to find the shortest path
     * from the start vertex to all other vertices in the graph.
     * Uses an array for distances and a boolean array for visited nodes.
     * Prints the shortest distance from start to every reachable vertex.
     *
     * @param start the ID of the starting vertex
     */
    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Dijkstra: start vertex " + start + " not found.");
            return;
        }

        int n = vertices.size();

        // dist[i] holds the shortest known distance from start to vertex i
        int[] dist = new int[n];

        // visited[i] is true once vertex i has been finalized
        boolean[] visited = new boolean[n];

        // Initialize all distances to infinity
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Repeat for every vertex
        for (int i = 0; i < n; i++) {

            // Pick the unvisited vertex with the smallest known distance
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }

            // If no reachable unvisited vertex remains, stop early
            if (u == -1 || dist[u] == Integer.MAX_VALUE) break;

            visited[u] = true;

            // Relax all edges going out from vertex u
            if (weightedAdjList.containsKey(u)) {
                for (int[] edge : weightedAdjList.get(u)) {
                    int neighbor = edge[0];
                    int weight   = edge[1];

                    if (!visited[neighbor] && dist[u] + weight < dist[neighbor]) {
                        dist[neighbor] = dist[u] + weight;
                    }
                }
            }
        }

        // Print results
        System.out.println("Dijkstra shortest paths from vertex " + start + ":");
        List<Integer> sortedKeys = new ArrayList<>(vertices.keySet());
        Collections.sort(sortedKeys);
        for (int v : sortedKeys) {
            String distance = (dist[v] == Integer.MAX_VALUE) ? "unreachable" : String.valueOf(dist[v]);
            System.out.println("  vertex " + v + " -> " + distance);
        }
    }

    /** Returns the number of vertices in the graph. */
    public int vertexCount() {
        return vertices.size();
    }

    /** Returns the total number of directed edges in the graph. */
    public int edgeCount() {
        int count = 0;
        for (List<int[]> edges : weightedAdjList.values()) {
            count += edges.size();
        }
        return count;
    }
}
