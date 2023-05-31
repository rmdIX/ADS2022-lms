package by.it.group151051.voronko.lesson12;

import static java.lang.System.out;

import java.util.List;

public class GraphTaskA {

    public static void main(String [] args) {

        Graph graph = new Graph();
        if (!graph.load("L12G1.txt")) {
            out.println("Ошибка чтения графа!");
            return;
        }

        graph.runDijkstra(1);
        List<Integer> distances = graph.getDistances();
        for (int i = 2; i <= graph.getNodeCount(); i++) {
            String path = graph.getPath(1, i);
            out.printf("Вершина %s: расстояние = %d, путь = %s\n", graph.getNodeName(i), distances.get(i - 1), path);
        }
    }
}