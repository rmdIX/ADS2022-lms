package by.it.group151051.goron.lesson11;

import java.util.*;

public class TaskB {

    public static void main(String[] args) {
        DirectedGraph dgraph = new DirectedGraph();

        dgraph.addEdge("A", "C");
        dgraph.addEdge("B", "C");
        dgraph.addEdge("C", "D");
        dgraph.addEdge("C", "E");
        dgraph.addEdge("D", "F");
        dgraph.addEdge("E", "F");
        dgraph.addEdge("F", "G");
        dgraph.addEdge("F", "H");

        dgraph.topologicalSort();
    }

    public static class DirectedGraph {
        private final Map<String, List<String>> vertexMap;
        private final Map<String, Boolean> visitedMap;
        private final Stack<String> topologicalOrdering;

        public DirectedGraph() {
            vertexMap = new HashMap<>();
            visitedMap = new LinkedHashMap<>();
            topologicalOrdering = new Stack<>();
        }

        public boolean hasVertex(String vertex) {
            return vertexMap.containsKey(vertex);
        }

        private void addVertex(String vertex) {
            if (!hasVertex(vertex)) {
                vertexMap.put(vertex, new ArrayList<>());
            }
        }

        public void addEdge(String startVertex, String endVertex) {
            if (!hasVertex(startVertex)) {
                addVertex(startVertex);
            }

            if (!hasVertex(endVertex)) {
                addVertex(endVertex);
            }

            List<String> adjacentVertexes1 = vertexMap.get(startVertex);
            adjacentVertexes1.add(endVertex);

            vertexMap.put(startVertex, adjacentVertexes1);
        }

        public void topologicalSort() {
            List<String> vertexList = new ArrayList<>(vertexMap.size());
            vertexList.addAll(vertexMap.keySet());
            Collections.sort(vertexList);

            for (String vertex : vertexList) {
                this.topologicalSortUtil(vertex);
            }

            System.out.println("Topological sort:");
            while (!topologicalOrdering.isEmpty()) {
                System.out.print(topologicalOrdering.pop() + " ");
            }

            System.out.println("\n");
        }

        private void topologicalSortUtil(String currentVertex) {
            if (visitedMap.containsKey(currentVertex)) {
                return;
            }

            visitedMap.put(currentVertex, true);

            List<String> adjacentVertexes = vertexMap.get(currentVertex);

            for (String adjacentVertex : adjacentVertexes) {
                if (visitedMap.containsKey(adjacentVertex)) {
                    continue;
                }

                topologicalSortUtil(adjacentVertex);
            }

            topologicalOrdering.push(currentVertex);
        }
    }
}