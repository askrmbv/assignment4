/**
 * Represents a single node (vertex) in a directed graph.
 * Each vertex has a unique integer identifier.
 */
public class Vertex {

    private int id;

    /** Constructs a new Vertex with the given unique identifier. */
    public Vertex(int id) {
        this.id = id;
    }

    /** Returns the unique identifier of this vertex. */
    public int getId() {
        return id;
    }

    /** Returns a string representation of this vertex. */
    @Override
    public String toString() {
        return "Vertex(" + id + ")";
    }
}
