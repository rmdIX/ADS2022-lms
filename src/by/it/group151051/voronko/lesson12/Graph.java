package by.it.group151051.voronko.lesson12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Graph {

    private static final String NODE_NAMES = "ABCDEFGHISJKLMNOPQRTUVWXYZ";

    public static final int INF = 1000000000;

    private int nodeCount;
    private boolean bidirectional;
    private List<Edge> edges;
    private int[][] matrix;

    private int [] parent;
    private int [] distance;

    public Graph() {
        nodeCount = 0;
        bidirectional = false;
        edges = null;
        matrix = null;
    }

    public Graph(int nodeCount, boolean bidirectional, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.bidirectional = bidirectional;
        this.edges = new ArrayList<>(edges);
        buildMatrix();
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public boolean load(String fileName) {
        try (Scanner scn = new Scanner(new FileInputStream(fileName))) {
            String line = scn.nextLine();
            String [] parts = line.split("[ \\t]+");
            if (parts.length < 2 || parts.length > 3) {
                throw new IOException("Bad graph size format");
            }
            int nodeCount = Integer.parseInt(parts[0]);
            if (nodeCount < 2 || nodeCount > 26) {
                throw new IOException("Bad node count value");
            }
            int edgeCount = Integer.parseInt(parts[1]);
            if (edgeCount < (nodeCount - 1) || edgeCount > nodeCount * (nodeCount - 1)) {
                throw new IOException("Bad edge count value");
            }
            bidirectional = parts.length > 2 && Integer.parseInt(parts[2]) != 0;
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < edgeCount; i++) {
                line = scn.nextLine();
                parts = line.split("[ \\t]+");
                if (parts.length < 2 || parts.length > 3) {
                    throw new IOException("Bad edge text format");
                }
                int src = Integer.parseInt(parts[0]);
                int dst = Integer.parseInt(parts[1]);
                int weight = parts.length > 2 ? Integer.parseInt(parts[2]) : 1;
                if (src < 1 || src > nodeCount || dst < 1 || dst > nodeCount) {
                    throw new IOException("Bad edge");
                }
                edges.add(new Edge(src, dst, weight));
            }
            scn.close();
            this.nodeCount = nodeCount;
            this.edges = edges;
            buildMatrix();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean save(String fileName) {
        try (PrintStream prn = new PrintStream(new FileOutputStream(fileName, true))) {
            prn.printf("%d %d %d\n", nodeCount, edges.size(), bidirectional ? 1 : 0);
            for (Edge edge : edges) {
                prn.printf("%d %d %d\n", edge.srcNode, edge.dstNode, edge.weight);
            }
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public String getNodeName(int node) {
        if (node > 26) {
            return null;
        }
        return NODE_NAMES.substring(node - 1, node);
    }

    public void reset() {
        distance = null;
        parent = null;
    }

    public void runDijkstra(int node) {
        distance = new int[nodeCount];
        parent = new int[nodeCount];
        int matrix[][] = new int[nodeCount][nodeCount];
        int start = node - 1;

        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                matrix[i][j] = this.matrix[i][j] != 0 ? this.matrix[i][j] : INF;
            }
        }

        HashSet<Integer> notVisited = new HashSet<>();
        for (int i = 0; i < nodeCount; i++) {
            notVisited.add(i);
            distance[i] = matrix[start][i];
            if (distance[i] < INF) {
                parent[i] = start;
            }
        }
        distance[start] = 0;
        parent[start] = -1;
        notVisited.remove(start);

        while (notVisited.size() > 0) {
            int minDistance = INF;
            int minNode = -1;
            for (Integer n : notVisited) {
                if (distance[n] < minDistance) {
                    minDistance = distance[n];
                    minNode = n;
                }
            }
            if (minNode != -1) {
                notVisited.remove(minNode);
                for (Integer n : notVisited) {
                    if (matrix[minNode][n] < INF) {
                        distance[n] = Math.min(distance[n], distance[minNode] + matrix[minNode][n]);
                        if (distance[n] == distance[minNode] + matrix[minNode][n]) {
                            parent[n] = minNode;
                        }
                    }
                }
            }
        }
    }

    public void runBellmanFord(int node) {
        distance = new int[nodeCount];
        parent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            distance[i] = INF;
            parent[i] = -1;
        }
        distance[node - 1] = 0;
        for (int k = 1; k < nodeCount; k++) {
            for (Edge edge: edges) {
                int start = edge.srcNode - 1;
                int finish = edge.dstNode - 1;
                int weight = edge.weight;
                if (distance[start] + weight < distance[finish]) {
                    distance[finish] = distance[start] + weight;
                    parent[finish] = start;
                }
            }
        }
    }

    public int findMinCycle(Edge edge, StringBuilder path) {
        runDijkstra(edge.dstNode);
        int d = getDistances().get(edge.srcNode - 1);
        if (d != INF) {
            d += edge.weight;
            path.append(getNodeName(edge.srcNode));
            path.append("-");
            path.append(getPath(edge.dstNode, edge.srcNode));
        }
        return d;
    }

    public List<Integer> getDistances() {
        if (distance == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        for (int d : distance) {
            result.add(d);
        }
        return result;
    }

    public String getPath(int start, int end) {
        if (parent == null) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        getPath(start - 1, end - 1, path);
        return path.stream().map(v -> getNodeName(v + 1)).collect(Collectors.joining("-"));
    }

    private void getPath(int start, int end, List<Integer> path) {
        if (start == end) {
            path.add(start);
            return;
        }
        int node = parent[end];
        if (node == start) {
            path.add(node);
            path.add(end);
            return;
        }
        getPath(start, node, path);
        path.add(end);
    }

    private void buildMatrix() {
        matrix = new int[nodeCount][nodeCount];
        for (Edge edge : edges) {
            matrix[edge.srcNode - 1][edge.dstNode - 1] = edge.weight;
            if (bidirectional) {
                matrix[edge.dstNode - 1][edge.srcNode - 1] = edge.weight;
            }
        }
    }

    public static class Edge {
        private int srcNode;
        private int dstNode;
        private int weight;

        public Edge(int src, int dst, int weight) {
            this.srcNode = src;
            this.dstNode = dst;
            this.weight = weight;
        }

        public Edge(int src, int dst) {
            this(src, dst, 1);
        }

        public int getSrcNode() {
            return srcNode;
        }

        public int getDstNode() {
            return dstNode;
        }

        public int getWeight() {
            return weight;
        }
    }

}
