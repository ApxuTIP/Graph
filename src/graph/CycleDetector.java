package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CycleDetector {

    private final Graph graph;

    public CycleDetector(Graph graph) {
        this.graph = graph;
    }

    public boolean hasCycle() {
        Set<Integer> visitedVertices = new HashSet<>();

        for (int vertex : graph.getVertices()) {
            if (!visitedVertices.contains(vertex)) {
                if (hasCycleFromVertex(vertex, visitedVertices)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleFromVertex(int startVertex, Set<Integer> visitedVertices) {
        Set<Integer> recursionStack = new HashSet<>();
        Deque<StackFrame> frameStack = new ArrayDeque<>();

        visitedVertices.add(startVertex);
        recursionStack.add(startVertex);
        frameStack.push(new StackFrame(startVertex, graph.getNeighbors(startVertex).iterator()));

        while (!frameStack.isEmpty()) {
            StackFrame currentFrame = frameStack.peek();
            Iterator<Edge> neighborIterator = currentFrame.neighborIterator();

            if (!neighborIterator.hasNext()) {
                recursionStack.remove(currentFrame.vertex());
                frameStack.pop();
                continue;
            }

            int neighborVertex = neighborIterator.next().toVertex();

            if (recursionStack.contains(neighborVertex)) {
                return true;
            }
            if (!visitedVertices.contains(neighborVertex)) {
                visitedVertices.add(neighborVertex);
                recursionStack.add(neighborVertex);
                frameStack.push(new StackFrame(neighborVertex, graph.getNeighbors(neighborVertex).iterator()));
            }
        }
        return false;
    }

    private record StackFrame(int vertex, Iterator<Edge> neighborIterator) {
    }
}