package graph;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Graph graph = buildTestGraph();

        GraphTraversal traversal = new GraphTraversal(graph);
        CycleDetector cycleDetector = new CycleDetector(graph);
        DijkstraPathFinder pathFinder = new DijkstraPathFinder(graph);

        System.out.println("DFS from 1: " + traversal.depthFirstSearch(1));
        System.out.println("BFS from 1: " + traversal.breadthFirstSearch(1));
        System.out.println("Components: " + traversal.countConnectedComponents());
        System.out.println("Has cycle: " + cycleDetector.hasCycle());

        Map<Integer, Integer> distances = pathFinder.findShortestDistances(1);
        System.out.println("Dijkstra from 1: " + distances);
    }

    private static Graph buildTestGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 2, 1);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 5, 10);
        return graph;
    }
}