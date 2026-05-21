import java.util.*;

/**
 * Handles performance analysis of BFS, DFS, and Dijkstra traversals.
 * Runs all three on graphs of different sizes and records execution times.
 */
public class Experiment {

    // Stores results as: graphSize -> {bfsTime, dfsTime, dijkstraTime} in nanoseconds
    private Map<Integer, long[]> results;

    /** Constructs an Experiment instance with an empty results map. */
    public Experiment() {
        results = new LinkedHashMap<>();
    }

    /**
     * Runs BFS, DFS, and Dijkstra on the given graph starting from vertex 0,
     * measures execution time for each, and stores the results.
     */
    public void runTraversals(Graph g) {
        int size = g.vertexCount();

        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsTime = System.nanoTime() - bfsStart;

        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsTime = System.nanoTime() - dfsStart;

        long dijkstraStart = System.nanoTime();
        g.dijkstra(0);
        long dijkstraTime = System.nanoTime() - dijkstraStart;

        results.put(size, new long[]{bfsTime, dfsTime, dijkstraTime});
    }

    /**
     * Runs traversals on three graph sizes: small (10), medium (30), large (100).
     * For each size, a random directed weighted graph is generated and traversed.
     */
    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        Random rand = new Random(42);

        for (int size : sizes) {
            Graph g = buildRandomGraph(size, rand);
            System.out.println("\n--- Graph size: " + size + " vertices ---");
            runTraversals(g);
        }
    }

    /**
     * Builds a random directed weighted graph with the given number of vertices.
     * Each vertex gets 2–4 random outgoing edges with weights between 1 and 10.
     */
    private Graph buildRandomGraph(int size, Random rand) {
        Graph g = new Graph();

        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }

        for (int i = 0; i < size; i++) {
            int edgeCount = 2 + rand.nextInt(3);
            Set<Integer> added = new HashSet<>();
            for (int j = 0; j < edgeCount; j++) {
                int dest   = rand.nextInt(size);
                int weight = 1 + rand.nextInt(10);
                if (dest != i && !added.contains(dest)) {
                    g.addEdge(i, dest, weight);
                    added.add(dest);
                }
            }
        }

        return g;
    }

    /**
     * Prints a formatted table of BFS, DFS, and Dijkstra execution times
     * for each tested graph size.
     */
    public void printResults() {
        System.out.println("\n====================================================");
        System.out.println("        Performance Results (nanoseconds)");
        System.out.println("====================================================");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Graph Size", "BFS (ns)", "DFS (ns)", "Dijkstra (ns)");
        System.out.println("----------------------------------------------------");

        for (Map.Entry<Integer, long[]> entry : results.entrySet()) {
            int size         = entry.getKey();
            long bfsTime     = entry.getValue()[0];
            long dfsTime     = entry.getValue()[1];
            long dijkstraTime = entry.getValue()[2];
            System.out.printf("%-15d %-15d %-15d %-15d%n", size, bfsTime, dfsTime, dijkstraTime);
        }

        System.out.println("====================================================");
    }
}
