package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphTraversal {

    private static final String UNKNOWN_VERTEX_MESSAGE = "Vertex is not present in graph";

    private final Graph graph;

    public GraphTraversal(Graph graph) {
        this.graph = graph;
    }

    public List<Integer> depthFirstSearch(int startVertex) {
        requireVertexExists(startVertex);

        List<Integer> visitOrder = new ArrayList<>();
        Set<Integer> visitedVertices = new HashSet<>();
        Deque<Integer> vertexStack = new ArrayDeque<>();

        vertexStack.push(startVertex);

        while (!vertexStack.isEmpty()) {
            int currentVertex = vertexStack.pop();

            if (visitedVertices.contains(currentVertex)) {
                continue;
            }
            visitedVertices.add(currentVertex);
            visitOrder.add(currentVertex);

            List<Edge> neighbors = graph.getNeighbors(currentVertex);
            for (int index = neighbors.size() - 1; index >= 0; index--) {
                int neighborVertex = neighbors.get(index).toVertex();
                if (!visitedVertices.contains(neighborVertex)) {
                    vertexStack.push(neighborVertex);
                }
            }
        }
        return visitOrder;
    }

    public List<Integer> breadthFirstSearch(int startVertex) {
        requireVertexExists(startVertex);

        List<Integer> visitOrder = new ArrayList<>();
        Set<Integer> visitedVertices = new HashSet<>();
        Deque<Integer> vertexQueue = new ArrayDeque<>();

        visitedVertices.add(startVertex);
        vertexQueue.add(startVertex);

        while (!vertexQueue.isEmpty()) {
            int currentVertex = vertexQueue.poll();
            visitOrder.add(currentVertex);

            for (Edge edge : graph.getNeighbors(currentVertex)) {
                int neighborVertex = edge.toVertex();
                if (!visitedVertices.contains(neighborVertex)) {
                    visitedVertices.add(neighborVertex);
                    vertexQueue.add(neighborVertex);
                }
            }
        }
        return visitOrder;
    }

    public int countConnectedComponents() {
        Set<Integer> visitedVertices = new HashSet<>();
        int componentCount = 0;

        for (int vertex : graph.getVertices()) {
            if (!visitedVertices.contains(vertex)) {
                componentCount++;
                markComponent(vertex, visitedVertices);
            }
        }
        return componentCount;
    }

    private void markComponent(int startVertex, Set<Integer> visitedVertices) {
        Deque<Integer> vertexStack = new ArrayDeque<>();
        vertexStack.push(startVertex);

        while (!vertexStack.isEmpty()) {
            int currentVertex = vertexStack.pop();

            if (visitedVertices.contains(currentVertex)) {
                continue;
            }
            visitedVertices.add(currentVertex);

            for (Edge edge : graph.getNeighbors(currentVertex)) {
                if (!visitedVertices.contains(edge.toVertex())) {
                    vertexStack.push(edge.toVertex());
                }
            }
        }
    }

    private void requireVertexExists(int vertex) {
        if (!graph.containsVertex(vertex)) {
            throw new IllegalArgumentException(UNKNOWN_VERTEX_MESSAGE);
        }
    }
}