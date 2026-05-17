public class Main {

    public static void main(String[] args) {

        System.out.println("=== Small Graph (10 vertices) — Manual ===\n");

        Graph smallGraph = new Graph();

        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        smallGraph.addEdge(0, 1);
        smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3);
        smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5);
        smallGraph.addEdge(2, 6);
        smallGraph.addEdge(3, 7);
        smallGraph.addEdge(4, 8);
        smallGraph.addEdge(5, 9);
        smallGraph.addEdge(6, 0);

        smallGraph.printGraph();
        System.out.println();

        long bfsStart = System.nanoTime();
        smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();
        System.out.println("BFS execution time: " + (bfsEnd - bfsStart) + " ns\n");

        long dfsStart = System.nanoTime();
        smallGraph.dfs(0);
        long dfsEnd = System.nanoTime();
        System.out.println("DFS execution time: " + (dfsEnd - dfsStart) + " ns\n");


        System.out.println("\n=== Performance Experiment (10 / 30 / 100 vertices) ===");

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
    }
}
