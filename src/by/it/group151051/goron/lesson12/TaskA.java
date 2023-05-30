package by.it.group151051.goron.lesson12;

import java.util.*;

public class TaskA {

    public static void main(String[] args) {
        Vertex vertA = new Vertex("A");
        Vertex vertB = new Vertex("B");
        Vertex vertC = new Vertex("C");
        Vertex vertD = new Vertex("D");
        Vertex vertE = new Vertex("E");
        Vertex vertF = new Vertex("F");
        Vertex vertG = new Vertex("G");
        Vertex vertH = new Vertex("H");

        vertA.addEdgeFromThisVertex(vertB, 1);
        vertA.addEdgeFromThisVertex(vertE, 4);
        vertA.addEdgeFromThisVertex(vertF, 8);

        vertB.addEdgeFromThisVertex(vertC, 2);
        vertB.addEdgeFromThisVertex(vertF, 6);
        vertB.addEdgeFromThisVertex(vertG, 6);

        vertC.addEdgeFromThisVertex(vertD, 1);
        vertC.addEdgeFromThisVertex(vertG, 2);

        vertD.addEdgeFromThisVertex(vertG, 1);
        vertD.addEdgeFromThisVertex(vertH, 4);

        vertE.addEdgeFromThisVertex(vertF, 5);

        vertG.addEdgeFromThisVertex(vertF, 1);
        vertG.addEdgeFromThisVertex(vertH, 1);

        WeightedGraph graph = new WeightedGraph();
        graph.addVertex(vertA);
        graph.addVertex(vertB);
        graph.addVertex(vertC);
        graph.addVertex(vertD);
        graph.addVertex(vertE);
        graph.addVertex(vertF);
        graph.addVertex(vertG);
        graph.addVertex(vertH);

        graph.dijkstraAlgorithm(vertA);
    }

    public static class Vertex {
        private final String name;
        private Integer distanceFromSource;
        private final Map<Vertex, Integer> adjacentVertexes;
        private List<Vertex> pathFromSource;

        public Vertex(String name) {
            this.name = name;
            this.distanceFromSource = Integer.MAX_VALUE;
            this.adjacentVertexes = new HashMap<>();
            this.pathFromSource = new LinkedList<>();
        }

        public void addEdgeFromThisVertex(Vertex destinationVertex, Integer weight) {
            adjacentVertexes.put(destinationVertex, weight);
        }

        public Integer getDistanceFromSource() {
            return distanceFromSource;
        }

        public String getPathFromSource() {
            StringBuilder path = new StringBuilder();
            int pathSize = pathFromSource.size();

            if (pathSize == 0) {
                path.append("[ ]");
            } else {
                path.append("[ ");
                for (int i = 0; i < pathSize - 1; ++i) {
                    path.append(pathFromSource.get(i).name).append(" -> ");
                }
                String lastVertexInPath = pathFromSource.get(pathSize - 1).name;
                path.append(lastVertexInPath).append(" ]");
            }

            return path.toString();
        }

        public void setDistanceFromSource(Integer distance) {
            this.distanceFromSource = distance;
        }

        public void setPathFromSource(List<Vertex> newPath) {
            this.pathFromSource = newPath;
        }

        public Map<Vertex, Integer> getAdjacentVertexes() {
            return adjacentVertexes;
        }

        /** Метод возвращает вес ребра, которое идет в текущую вершину */
        public Integer getWeightOfEdge(Vertex sourceVertex) {
            return sourceVertex.getAdjacentVertexes().get(this);
        }

        public void calculateNewDistanceFromSource(Vertex prevVertex) {
            int newDistanceFromSource = prevVertex.getDistanceFromSource() + this.getWeightOfEdge(prevVertex);

            if (newDistanceFromSource < this.getDistanceFromSource()) {
                this.setDistanceFromSource(newDistanceFromSource);
                List<Vertex> newPathFromSource = new LinkedList<>(prevVertex.pathFromSource);
                newPathFromSource.add(this);
                this.setPathFromSource(newPathFromSource);
            }
        }
    }

    public static class WeightedGraph {
        private final Set<Vertex> vertexes;

        public WeightedGraph() {
            this.vertexes = new LinkedHashSet<>();
        }

        public void addVertex(Vertex vertex) {
            this.vertexes.add(vertex);
        }

        public void dijkstraAlgorithm(Vertex source) {
            source.setDistanceFromSource(0);

            vertexes.forEach(vertex -> vertex.pathFromSource.add(source));

            Set<Vertex> vertexesToVisit = new HashSet<>();
            Set<Vertex> visitedVertexes = new HashSet<>();

            vertexesToVisit.add(source);

            while (!vertexesToVisit.isEmpty()) {
                Vertex currentVertex = getClosestVertex(vertexesToVisit);
                vertexesToVisit.remove(currentVertex);

                for (Vertex adjacentVertex : currentVertex.getAdjacentVertexes().keySet()) {
                    if (!visitedVertexes.contains(adjacentVertex)) {
                        adjacentVertex.calculateNewDistanceFromSource(currentVertex);
                        vertexesToVisit.add(adjacentVertex);
                    }
                }

                visitedVertexes.add(currentVertex);
            }

            printPathsFromSource(source);
        }

        public Vertex getClosestVertex(Set<Vertex> vertexesSet) {
            Vertex closestVertex = null;
            Integer lowestDist = Integer.MAX_VALUE;

            for (Vertex vertex : vertexesSet) {
                Integer distFromSource = vertex.getDistanceFromSource();

                if (distFromSource < lowestDist) {
                    lowestDist = distFromSource;
                    closestVertex = vertex;
                }
            }

            return closestVertex;
        }

        private void printPathsFromSource(Vertex source) {
            System.out.println("Path to all vertexes from " + source.name + ":");
            for (Vertex vertex : vertexes) {
                int pathSize = vertex.distanceFromSource;
                System.out.println(vertex.name + ": " + vertex.getPathFromSource());
                System.out.println("distance: " + pathSize + "\n");
            }
        }

    }
}
