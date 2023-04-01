package by.it.group151051.yankovich.lesson11;

import java.util.*;

public class Graph {
    private Map<Character, List<Character>> adjVertices;
    private Map<Character, Boolean> visited;
    private Map<Character, Integer> preValues;
    private Map<Character, Integer> postValues;
    private Map<Character, EdgeType> edgeTypes;

    public enum EdgeType {
        TREE, BACK
    }

    public Graph() {
        adjVertices = new HashMap<>();
        visited = new HashMap<>();
        preValues = new HashMap<>();
        postValues = new HashMap<>();
        edgeTypes = new HashMap<>();
    }

    public void addVertex(Character vertex) {
        adjVertices.put(vertex, new ArrayList<>());
        visited.put(vertex, false);
        preValues.put(vertex, 0);
        postValues.put(vertex, 0);
    }

    public void addEdge(Character source, Character destination) {
        adjVertices.get(source).add(destination);
    }

    public List<Character> getAdjVertices(Character vertex) {
        return adjVertices.get(vertex);
    }

    public void setVisited(Character vertex, boolean value) {
        visited.put(vertex, value);
    }

    public boolean isVisited(Character vertex) {
        return visited.get(vertex);
    }

    public void dfs(Character vertex, int[] preCounter, int[] postCounter) {
        setVisited(vertex, true);
        preValues.put(vertex, preCounter[0]++);
        for (Character v : getAdjVertices(vertex)) {
            if (!isVisited(v)) {
                edgeTypes.put(v, EdgeType.TREE);
                dfs(v, preCounter, postCounter);
            } else if (preValues.get(v) > preValues.get(vertex)) {
                edgeTypes.put(v, EdgeType.BACK);
            }
        }
        postValues.put(vertex, postCounter[0]++);
    }

    public void printTraversal() {
        System.out.println("Vertex\tPre\tPost\tEdgeType");
        for (Character v : adjVertices.keySet()) {
            System.out.println(v + "\t\t" + preValues.get(v) + "\t" + postValues.get(v) + "\t\t" + edgeTypes.get(v));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('I');

        // Add edges
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'C');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'F');
        graph.addEdge('E', 'F');
        graph.addEdge('F', 'I');
        graph.addEdge('D', 'H');
        graph.addEdge('G', 'H');

        // Perform DFS
        int[] preCounter = {1};
        int[] postCounter = {1};
        for (Character v : graph.adjVertices.keySet()) {
            if (!graph.isVisited(v)) {
                graph.dfs(v, preCounter, postCounter);
            }
        }

        // Print traversal
        graph.printTraversal();
    }
}


