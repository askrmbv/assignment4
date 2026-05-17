import java.util.*;

/**
 * Handles the execution and performance analysis of graph traversals.
 * Runs BFS and DFS on graphs of different sizes and records execution times.
 */
public class Experiment {

    // Stores results as: graphSize -> {bfsTime, dfsTime} in nanoseconds
    private Map<Integer, long[]> results;

    /** Constructs an Experiment instance with an empty results map. */
    public Experiment() {
        results = new LinkedHashMap<>();
    }

    /**
     * Runs both BFS and DFS on the given graph starting from vertex 0,
     * measures execution time for each, and stores the results.
     *
     * @param g the graph to traverse
     */
    public void runTraversals(Graph g) {
        int size = g.vertexCount();

        // Measure BFS time
        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;

        // Measure DFS time
        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        results.put(size, new long[]{bfsTime, dfsTime});
    }

    /**
     * Runs traversals on three graph sizes: small (10), medium (30), large (100).
     * For each size, a random directed graph is generated and traversed.
     */
    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        Random rand = new Random(42); // fixed seed for reproducibility

        for (int size : sizes) {
            Graph g = buildRandomGraph(size, rand);
            System.out.println("\n--- Graph size: " + size + " vertices ---");
            runTraversals(g);
        }
    }

    /**
     * Builds a random directed graph with the given number of vertices.
     * Each vertex gets approximately 2–4 random outgoing edges.
     *
     * @param size number of vertices
     * @param rand Random instance for reproducibility
     * @return the constructed Graph
     */
    private Graph buildRandomGraph(int size, Random rand) {
        Graph g = new Graph();

        // Add all vertices
        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }

        // Add random directed edges (each vertex gets 2–4 outgoing edges)
        for (int i = 0; i < size; i++) {
            int edgeCount = 2 + rand.nextInt(3);
            Set<Integer> added = new HashSet<>();
            for (int j = 0; j < edgeCount; j++) {
                int dest = rand.nextInt(size);
                if (dest != i && !added.contains(dest)) {
                    g.addEdge(i, dest);
                    added.add(dest);
                }
            }
        }

        return g;
    }

    /**
     * Prints a formatted table of BFS and DFS execution times
     * for each tested graph size.
     */
    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("   Performance Results (nanoseconds)");
        System.out.println("========================================");
        System.out.printf("%-15s %-15s %-15s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("----------------------------------------");

        for (Map.Entry<Integer, long[]> entry : results.entrySet()) {
            int size = entry.getKey();
            long bfsTime = entry.getValue()[0];
            long dfsTime = entry.getValue()[1];
            System.out.printf("%-15d %-15d %-15d%n", size, bfsTime, dfsTime);
        }

        System.out.println("========================================");
    }
}
