package by.it.group151051.voronko.lesson11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {

    private static final String NODE_NAMES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int nodeCount;
    private boolean bidirectional;
    private List<Edge> edges;
    private int[][] matrix;

    private int[] timeIn;
    private int[] timeOut;
    private int [] parent;
    private int [] mark;
    private int[] topology;
    private List<Edge> treeEdges = new ArrayList<>();
    private List<Edge> backEdges = new ArrayList<>();
    private int time;
    private int topologyNumber;

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

    public void findSourcesAndDestinations(List<Integer> sources, List<Integer> destinations) {
        if (bidirectional) {
            return;
        }
        for (int n = 0; n < nodeCount; n++) {
            int outCount = 0, inCount = 0;
            for (int m = 0; m < nodeCount; m++) {
                if (matrix[n][m] != 0) {
                    outCount++;
                }
                if (matrix[m][n] != 0) {
                    inCount++;
                }
            }
            if (outCount == 0 && inCount > 0) {
                destinations.add(n+1);
            }
            if (outCount > 0 && inCount == 0) {
                sources.add(n+1);
            }
        }
    }

    public void resetCalculations() {
        parent = null;
        timeIn = null;
        timeOut = null;
        mark = null;
        topology = null;
        treeEdges.clear();
        backEdges.clear();
        time = 0;
    }

    public void calculate() {
        mark = new int[nodeCount];
        parent = new int[nodeCount];
        timeIn = new int[nodeCount];
        timeOut = new int[nodeCount];
        topology = new int[nodeCount];
        time = 0;
        topologyNumber = nodeCount;
        for (int i = 0; i < nodeCount; i++) {
            if (mark[i] == 0) {
                dfs(i);
            }
        }
        for (Edge edge : edges) {
            int src = edge.srcNode - 1;
            int dst = edge.dstNode - 1;
            if (parent[dst] == src || (bidirectional && parent[src] == dst)) {
                treeEdges.add(edge);
            } else if (timeIn[dst] < timeIn[src] && timeOut[src] < timeOut[dst]) {
                backEdges.add(edge);
            } else if (bidirectional && (timeIn[src] < timeIn[dst]) && (timeOut[dst] < timeOut[src])) {
                backEdges.add(edge);
            }
        }
    }

    public int getTimeIn(int node) {
        if (timeIn == null || node < 1 || node > nodeCount) {
            return -1;
        }
        return timeIn[node - 1];
    }

    public int getTimeOut(int node) {
        if (timeOut == null || node < 1 || node > nodeCount) {
            return -1;
        }
        return timeOut[node - 1];
    }

    public int getTopologyNumber(int node) {
        if (topology == null || node < 1 || node > nodeCount) {
            return -1;
        }
        return topology[node - 1];
    }

    public List<Edge> getTreeEdges() {
        return treeEdges;
    }

    public List<Edge> getBackEdges() {
        return backEdges;
    }

    private void dfs(int node) {
        mark[node] = 1;
        timeIn[node] = time++;
        for (int i = 0; i < nodeCount; i++) {
            if (matrix[node][i] != 0 && mark[i] == 0) {
                parent[i] = node;
                dfs(i);
            }
        }
        mark[node] = 2;
        timeOut[node] = time++;
        topology[node] = topologyNumber--;
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
