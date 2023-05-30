package by.it.group151051.voronko.lesson12;


import java.util.List;

import static java.lang.System.out;

public class GraphTaskB {

    public static void main(String [] args) {
        Graph graph = new Graph();
        if (!graph.load("L12G2.txt")) {
            out.println("Ошибка чтения графа!");
            return;
        }

        graph.runBellmanFord(10);
        List<Integer> distances = graph.getDistances();
        for (int i = 1; i <= graph.getNodeCount() - 1; i++) {
            String path = graph.getPath(10, i);
            out.printf("Вершина %s: расстояние = %d, путь = %s\n", graph.getNodeName(i), distances.get(i - 1), path);
        }
    }
}
