package by.it.group151051.padabied.lesson12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB {

    public static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static ArrayList<Edge> getGraph() {
        ArrayList<Edge> graph = new ArrayList<>();
        graph.add(new Edge(0, 2, -2)); //AC
        graph.add(new Edge(0, 1, 4));  //AB
        graph.add(new Edge(1, 6, -2)); //BG
        graph.add(new Edge(1, 7, -4)); //BH
        graph.add(new Edge(2, 3, 2));  //CD
        graph.add(new Edge(2, 5, 1));  //CF
        graph.add(new Edge(4, 5, -2)); //EF
        graph.add(new Edge(4, 7, 3));  //EH
        graph.add(new Edge(5, 3, 3));  //FD
        graph.add(new Edge(6, 8, -1)); //GI
        graph.add(new Edge(7, 6, 1));  //HG
        graph.add(new Edge(8, 7, 1));  //IH
        graph.add(new Edge(9, 0, 7));  //SA
        graph.add(new Edge(9, 2, 6));  //SC
        graph.add(new Edge(9, 5, 5));  //SF
        graph.add(new Edge(9, 4, 6));  //SE
        return graph;
    }

    public static void bellman(ArrayList<Edge> graph, int start, int size) {
        int[] distance = new int[size];
        int[] parent = new int[size];

        Arrays.fill(distance, Integer.MAX_VALUE); //Изначально путь до каждой вершины = infinity
        distance[start] = 0;                      //Путь от начала до начала = 0

        Arrays.fill(parent, -1);

        for (int i = 0; i < size-1; i++) {
            for(Edge edge : graph) {
                int u = edge.start;
                int v = edge.end;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v])
                {
                    distance[v] = distance[u] + w;
                    parent[v] = u;
                }
            }
        }
        for (int i = 0; i < size; i++)
        {
            if (i != start && distance[i] < Integer.MAX_VALUE) {
                List<Integer> path = new ArrayList<>();
                getPath(parent, i, path);
                System.out.print("Расстояние от вершины " + (char)(65 + start)  + " до вершины " +
                        (char) (65 + i)  + " равно " + distance[i]);
                System.out.print( " Путь: " );
                for (int j = 0; j < path.size(); j++) {
                    System.out.print((char)(65+path.get(j)));
                    if (j != path.size()-1) System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    public static void getPath(int[] parent, int vertex, List<Integer> path) {
        if (vertex < 0) {
            return;
        }
        getPath(parent, parent[vertex], path);
        path.add(vertex);
    }
    public static void main(String[] args) {
        ArrayList<Edge> graph = getGraph();
        for (int i = 0; i < graph.size(); i++) {
            bellman(graph, i, graph.size());
        }
    }
}
