/**
 * Represents a directed edge between two vertices in the graph.
 * An edge goes from a source vertex to a destination vertex.
 */
public class Edge {

    private Vertex source;
    private Vertex destination;

    /** Constructs a directed edge from source to destination. */
    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    /** Returns the source (starting) vertex of this edge. */
    public Vertex getSource() {
        return source;
    }

    /** Returns the destination (ending) vertex of this edge. */
    public Vertex getDestination() {
        return destination;
    }

    /** Returns a string representation of this directed edge. */
    @Override
    public String toString() {
        return source.getId() + " -> " + destination.getId();
    }
}
