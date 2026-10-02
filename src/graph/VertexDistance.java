package graph;

public record VertexDistance(int vertex, int distance) implements Comparable<VertexDistance> {

    @Override
    public int compareTo(VertexDistance other) {
        return Integer.compare(distance(), other.distance());
    }
}