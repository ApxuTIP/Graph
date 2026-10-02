package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private static final String NEGATIVE_WEIGHT_MESSAGE = "Edge weight must be non-negative";

    private final Map<Integer, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int fromVertex, int toVertex, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException(NEGATIVE_WEIGHT_MESSAGE);
        }
        addVertex(fromVertex);
        addVertex(toVertex);
        adjacencyList.get(fromVertex).add(new Edge(toVertex, weight));
    }

    public List<Edge> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Integer> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public boolean containsVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }
}