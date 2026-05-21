/**
 * Entry point for the Graph Traversal and Representation System.
 *
 * Demonstrates:
 *   - Manual construction of a small directed weighted graph
 *   - BFS and DFS traversals with timing
 *   - Dijkstra's algorithm for shortest paths
 *   - Performance experiments on graphs of 10, 30, and 100 vertices
 */
public class Main {

    public static void main(String[] args) {

        // -------------------------------------------------------
        // Part 1: Small directed weighted graph — manual construction
        // -------------------------------------------------------
        System.out.println("=== Small Graph (10 vertices) — Manual ===\n");

        Graph smallGraph = new Graph();

        // Add 10 vertices (0–9)
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        // Add directed weighted edges
        smallGraph.addEdge(0, 1, 4);
        smallGraph.addEdge(0, 2, 1);
        smallGraph.addEdge(1, 3, 1);
        smallGraph.addEdge(1, 4, 5);
        smallGraph.addEdge(2, 5, 2);
        smallGraph.addEdge(2, 6, 8);
        smallGraph.addEdge(3, 7, 3);
        smallGraph.addEdge(4, 8, 2);
        smallGraph.addEdge(5, 9, 6);
        smallGraph.addEdge(6, 0, 3);  // back edge to create a cycle

        // Print graph structure
        smallGraph.printGraph();
        System.out.println();

        // BFS traversal with timing
        long bfsStart = System.nanoTime();
        smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();
        System.out.println("BFS execution time: " + (bfsEnd - bfsStart) + " ns\n");

        // DFS traversal with timing
        long dfsStart = System.nanoTime();
        smallGraph.dfs(0);
        long dfsEnd = System.nanoTime();
        System.out.println("DFS execution time: " + (dfsEnd - dfsStart) + " ns\n");

        // -------------------------------------------------------
        // Part 2: Dijkstra's algorithm — shortest paths from vertex 0
        // -------------------------------------------------------
        System.out.println("=== Bonus: Dijkstra's Algorithm ===\n");

        long dijkstraStart = System.nanoTime();
        smallGraph.dijkstra(0);
        long dijkstraEnd = System.nanoTime();
        System.out.println("Dijkstra execution time: " + (dijkstraEnd - dijkstraStart) + " ns\n");

        // -------------------------------------------------------
        // Part 3: Performance experiment on multiple graph sizes
        // -------------------------------------------------------
        System.out.println("\n=== Performance Experiment (10 / 30 / 100 vertices) ===");

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
    }
}
