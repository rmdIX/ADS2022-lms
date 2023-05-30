package by.it.group151051.voronko.lesson12;

import static java.lang.System.out;

public class GraphTaskC {

    public static void main(String [] args) {
        Graph graph = new Graph();
        if (!graph.load("L12G3.txt")) {
            out.println("Ошибка чтения графа!");
            return;
        }

        Graph.Edge edge = new Graph.Edge(5, 1, 2);
        StringBuilder path = new StringBuilder();
        int length = graph.findMinCycle(edge, path);
        if (length != Graph.INF) {
            out.printf("Цикл:%s, длина цикла = %d", path, length);
        } else {
            out.println("Цикл не найден!");
        }
    }
}

