package by.it.group151051.goron.lesson11;

import java.util.*;

public class TaskA {

    public static void main(String[] args) {
        UndirectedGraph ugraph = new UndirectedGraph();

        ugraph.addEdge("A", "B");
        ugraph.addEdge("A", "E");
        ugraph.addEdge("B", "C");
        ugraph.addEdge("B", "E");
        ugraph.addEdge("C", "F");
        ugraph.addEdge("F", "E");
        ugraph.addEdge("F", "I");

        ugraph.addEdge("D", "G");
        ugraph.addEdge("D", "H");
        ugraph.addEdge("G", "H");

        Map<String, List<String>> vertexMap = ugraph.getVertexMap();

        List<String> vertexList = new ArrayList<>(vertexMap.size());
        vertexList.addAll(vertexMap.keySet());
        Collections.sort(vertexList);

        for (String vertex : vertexList) {
            ugraph.DFS(vertex, vertex);
        }

        ugraph.printEdgesTypes();
        System.out.println();

        Map<String, vertexTimestamps> visited = ugraph.getVisitedMap();

        for (Map.Entry<String, vertexTimestamps> entry : visited.entrySet()) {
            vertexTimestamps vertex = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), vertex.pre, vertex.post);
        }
    }

    private static class UndirectedGraph {
        private final Map<String, List<String>> vertexMap;
        private final Map<String, vertexTimestamps> visitedMap;
        private final List<Edge> edges;
        private int time = 1;

        public UndirectedGraph() {
            vertexMap = new HashMap<>();
            visitedMap = new LinkedHashMap<>();
            edges = new LinkedList<>();
        }

        public boolean hasVertex(String vertex) {
            return vertexMap.containsKey(vertex);
        }

        private void addVertex(String vertex) {
            if (!hasVertex(vertex)) {
                vertexMap.put(vertex, new ArrayList<>());
            }
        }

        public void addEdge(String vertex1, String vertex2) {
            if (!hasVertex(vertex1)) {
                addVertex(vertex1);
            }

            if (!hasVertex(vertex2)) {
                addVertex(vertex2);
            }

            List<String> adjacentVertexes1 = vertexMap.get(vertex1);
            List<String> adjacentVertexes2 = vertexMap.get(vertex2);
            adjacentVertexes1.add(vertex2);
            adjacentVertexes2.add(vertex1);

            vertexMap.put(vertex1, adjacentVertexes1);
            vertexMap.put(vertex2, adjacentVertexes2);
        }

        public Map<String, List<String>> getVertexMap() {
            return vertexMap;
        }

        public Map<String, vertexTimestamps> getVisitedMap() {
            return visitedMap;
        }

        public void DFS(String currentVertex, String parentVertex) {
            if (visitedMap.containsKey(currentVertex)) {
                return;
            }

            visitedMap.put(currentVertex, new vertexTimestamps(time, -1));
            ++time;

            List<String> adjacentVertexes = vertexMap.get(currentVertex);

            for (String adjacentVertex : adjacentVertexes) {
                if (visitedMap.containsKey(adjacentVertex)) {
                    if (!adjacentVertex.equals(parentVertex) && adjacentVertex.compareTo(currentVertex) < 0) {
                        Edge newEdge = new Edge(currentVertex, adjacentVertex, "back");
                        edges.add(newEdge);
                    }

                    continue;
                }

                Edge newEdge = new Edge(currentVertex, adjacentVertex, "tree");
                edges.add(newEdge);

                DFS(adjacentVertex, currentVertex);
            }

            vertexTimestamps vertex = visitedMap.get(currentVertex);
            vertex.post = time;
            ++time;
        }

        public void printEdgesTypes() {
            for (Edge edge : edges) {
                System.out.format("(%1$s, %2$s) - %3$s edge\n", edge.startVertex, edge.endVertex, edge.type);
            }
        }
    }

    public static class vertexTimestamps {
        private int pre;
        private int post;

        public vertexTimestamps(int pre, int post) {
            this.pre = pre;
            this.post = post;
        }
    }

    public record Edge(String startVertex, String endVertex, String type) {}
}