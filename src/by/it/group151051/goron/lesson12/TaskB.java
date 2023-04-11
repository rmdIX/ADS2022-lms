package by.it.group151051.goron.lesson12;

import java.util.*;

public class TaskB {

    public static void main(String[] args) {
        Vertex vertA = new Vertex("A");
        Vertex vertB = new Vertex("B");
        Vertex vertC = new Vertex("C");
        Vertex vertD = new Vertex("D");
        Vertex vertE = new Vertex("E");
        Vertex vertF = new Vertex("F");
        Vertex vertG = new Vertex("G");
        Vertex vertH = new Vertex("H");
        Vertex vertI = new Vertex("I");
        Vertex vertS = new Vertex("S");


        vertA.addEdgeFromThisVertex(vertB, 4);
        vertA.addEdgeFromThisVertex(vertC, -2);

        vertB.addEdgeFromThisVertex(vertG, -2);
        vertB.addEdgeFromThisVertex(vertH, -4);

        vertC.addEdgeFromThisVertex(vertD, 2);
        vertC.addEdgeFromThisVertex(vertF, 1);

        vertE.addEdgeFromThisVertex(vertF, -2);
        vertE.addEdgeFromThisVertex(vertH, 3);

        vertG.addEdgeFromThisVertex(vertI, -1);

        vertH.addEdgeFromThisVertex(vertG, 1);

        vertI.addEdgeFromThisVertex(vertH, 1);

        vertS.addEdgeFromThisVertex(vertA, 7);
        vertS.addEdgeFromThisVertex(vertC, 6);
        vertS.addEdgeFromThisVertex(vertE, 6);
        vertS.addEdgeFromThisVertex(vertF, 5);

        WeightedGraph graph = new WeightedGraph();
        graph.addVertex(vertA);
        graph.addVertex(vertB);
        graph.addVertex(vertC);
        graph.addVertex(vertD);
        graph.addVertex(vertE);
        graph.addVertex(vertF);
        graph.addVertex(vertG);
        graph.addVertex(vertH);
        graph.addVertex(vertI);
        graph.addVertex(vertS);

        graph.bellmanFordAlgorithm(vertS);
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

        /** Метод возвращает true, в случае обновления дистанции от источника. Иначе, возвращает false */
        public boolean calculateNewDistanceFromSource(Vertex prevVertex) {
            int newDistanceFromSource = prevVertex.getDistanceFromSource() + this.getWeightOfEdge(prevVertex);

            if (newDistanceFromSource < this.getDistanceFromSource()) {
                this.setDistanceFromSource(newDistanceFromSource);
                List<Vertex> newPathFromSource = new LinkedList<>(prevVertex.pathFromSource);
                newPathFromSource.add(this);
                this.setPathFromSource(newPathFromSource);

                return true;
            }

            return false;
        }
    }

    public static class WeightedGraph {
        private final Set<Vertex> vertexes;
        private int numberOfVertexes = 0;

        public WeightedGraph() {
            this.vertexes = new LinkedHashSet<>();
        }

        public void addVertex(Vertex vertex) {
            this.vertexes.add(vertex);
            ++numberOfVertexes;
        }

        public void bellmanFordAlgorithm(Vertex source) {
            source.setDistanceFromSource(0);

            vertexes.forEach(vertex -> vertex.pathFromSource.add(source));

            Stack<Vertex> vertexesToVisit = new Stack<>();
            Set<Vertex> visitedVertexes = new HashSet<>();

            for (int i = 1; i < numberOfVertexes; ++i) {
                boolean isDistanceUpdated = false;

                vertexesToVisit.push(source);

                while (!vertexesToVisit.isEmpty()) {
                    Vertex currentVertex = vertexesToVisit.pop();

                    for (Vertex adjacentVertex : currentVertex.getAdjacentVertexes().keySet()) {
                        if (!visitedVertexes.contains(adjacentVertex)) {
                            isDistanceUpdated = adjacentVertex.calculateNewDistanceFromSource(currentVertex);
                            vertexesToVisit.push(adjacentVertex);
                        } else {
                            isDistanceUpdated = adjacentVertex.calculateNewDistanceFromSource(currentVertex);
                        }
                    }

                    visitedVertexes.add(currentVertex);
                }

                visitedVertexes.clear();

                if (!isDistanceUpdated) {
                    break;
                }
            }

            printPathsFromSource(source);
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
