/**
 * Represents a directed, weighted edge between two vertices in the graph.
 * An edge goes from a source vertex to a destination vertex and carries an integer weight.
 */
public class Edge {

    private Vertex source;
    private Vertex destination;
    private int weight;

    /** Constructs a directed weighted edge from source to destination. */
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /** Returns the source (starting) vertex of this edge. */
    public Vertex getSource() {
        return source;
    }

    /** Returns the destination (ending) vertex of this edge. */
    public Vertex getDestination() {
        return destination;
    }

    /** Returns the weight of this edge. */
    public int getWeight() {
        return weight;
    }

    /** Returns a string representation of this directed weighted edge. */
    @Override
    public String toString() {
        return source.getId() + " -(" + weight + ")-> " + destination.getId();
    }
}
