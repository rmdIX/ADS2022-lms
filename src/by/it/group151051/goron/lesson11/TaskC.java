package by.it.group151051.goron.lesson11;

import java.util.*;

public class TaskC {

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

        Map<String, vertexTimestamps> visited = dgraph.getVisitedMap();

        System.out.println("Pre- and post- values:");
        for (Map.Entry<String, vertexTimestamps> entry : visited.entrySet()) {
            vertexTimestamps vertex = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), vertex.pre, vertex.post);
        }

        dgraph.findSourcesAndSinks();

        dgraph.resetVisitedMap();
        System.out.println("\nAll topological orderings:");
        dgraph.findAllTopologicalOrderings();
        dgraph.getNumOfTopologicalOrderings();
    }

    public static class DirectedGraph {
        private final Map<String, List<String>> vertexMap;
        private Map<String, vertexTimestamps> visitedMap;
        private final Stack<String> topologicalOrdering;
        private final Map<String, Integer> indegree;
        private int time = 1;
        private int graphSize = 0;
        private int topologicalOrderings = 0;

        public DirectedGraph() {
            vertexMap = new HashMap<>();
            visitedMap = new LinkedHashMap<>();
            topologicalOrdering = new Stack<>();
            indegree = new HashMap<>();
        }

        public boolean hasVertex(String vertex) {
            return vertexMap.containsKey(vertex);
        }

        private void addVertex(String vertex) {
            if (!hasVertex(vertex)) {
                vertexMap.put(vertex, new ArrayList<>());
                indegree.put(vertex, 0);
                ++graphSize;
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

            indegree.put(endVertex, indegree.get(endVertex) + 1);
        }

        public Map<String, vertexTimestamps> getVisitedMap() {
            return visitedMap;
        }

        public void resetVisitedMap() {
            visitedMap = new HashMap<>();
        }

        public void findSourcesAndSinks() {
            List<String> sources = new LinkedList<>();
            List<String> sinks = new LinkedList<>();

            List<String> vertexList = new ArrayList<>(vertexMap.size());
            vertexList.addAll(vertexMap.keySet());
            Collections.sort(vertexList);

            for (String vertex : vertexList) {
                if (indegree.get(vertex) == 0) {
                    sources.add(vertex);
                } else if (vertexMap.get(vertex).isEmpty()) {
                    sinks.add(vertex);
                }
            }

            System.out.println("\nSources of graph:");
            for (String source : sources) {
                System.out.print(source + " ");
            }

            System.out.println("\nSinks of graph:");
            for (String sink : sinks) {
                System.out.print(sink + " ");
            }
            System.out.println();
        }

        public void findAllTopologicalOrderings() {
            List<String> vertexList = new ArrayList<>(vertexMap.size());
            vertexList.addAll(vertexMap.keySet());
            Collections.sort(vertexList);

            for (String vertex : vertexList) {
                if (indegree.get(vertex) == 0 && !visitedMap.containsKey(vertex)) {
                    List<String> adjacentVertexesList = vertexMap.get(vertex);
                    for (String adjacentVertex : adjacentVertexesList) {
                        indegree.put(adjacentVertex, indegree.get(adjacentVertex) - 1);
                    }

                    topologicalOrdering.push(vertex);
                    visitedMap.put(vertex, null);

                    findAllTopologicalOrderings();

                    for (String adjacentVertex : adjacentVertexesList) {
                        indegree.put(adjacentVertex, indegree.get(adjacentVertex) + 1);
                    }

                    topologicalOrdering.pop();
                    visitedMap.remove(vertex);
                }
            }

            if (topologicalOrdering.size() == graphSize) {
                System.out.println(topologicalOrdering);
                ++topologicalOrderings;
            }
        }

        public void getNumOfTopologicalOrderings() {
            System.out.println("Total: " + topologicalOrderings);
        }

        public void topologicalSort() {
            List<String> vertexList = new ArrayList<>(vertexMap.size());
            vertexList.addAll(vertexMap.keySet());
            Collections.sort(vertexList);

            for (String vertex : vertexList) {
                topologicalSortUtil(vertex);
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

            visitedMap.put(currentVertex, new vertexTimestamps(time, -1));
            ++time;

            List<String> adjacentVertexes = vertexMap.get(currentVertex);

            for (String adjacentVertex : adjacentVertexes) {
                if (visitedMap.containsKey(adjacentVertex)) {
                    continue;
                }

                topologicalSortUtil(adjacentVertex);
            }

            vertexTimestamps vertex = visitedMap.get(currentVertex);
            vertex.post = time;
            ++time;

            topologicalOrdering.push(currentVertex);
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
}