package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraPathFinder {

    public static final int UNREACHABLE_DISTANCE = Integer.MAX_VALUE;
    private static final String UNKNOWN_VERTEX_MESSAGE = "Vertex is not present in graph";

    private final Graph graph;

    public DijkstraPathFinder(Graph graph) {
        this.graph = graph;
    }

    public Map<Integer, Integer> findShortestDistances(int startVertex) {
        requireVertexExists(startVertex);

        Map<Integer, Integer> shortestDistances = new HashMap<>();
        for (int vertex : graph.getVertices()) {
            shortestDistances.put(vertex, UNREACHABLE_DISTANCE);
        }
        shortestDistances.put(startVertex, 0);

        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new VertexDistance(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            VertexDistance current = priorityQueue.poll();

            if (current.distance() > shortestDistances.get(current.vertex())) {
                continue;
            }

            for (Edge edge : graph.getNeighbors(current.vertex())) {
                int neighborVertex = edge.toVertex();
                int newDistance = current.distance() + edge.weight();

                if (newDistance < shortestDistances.get(neighborVertex)) {
                    shortestDistances.put(neighborVertex, newDistance);
                    priorityQueue.add(new VertexDistance(neighborVertex, newDistance));
                }
            }
        }
        return shortestDistances;
    }

    private void requireVertexExists(int vertex) {
        if (!graph.containsVertex(vertex)) {
            throw new IllegalArgumentException(UNKNOWN_VERTEX_MESSAGE);
        }
    }
}